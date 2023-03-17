package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.charts.Pie;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Locale;

public class ThongKe extends AppCompatActivity {

    private PieChart pieChart;
    private Adapter_ListView_ThongKe adapter_listView_thongKe;
    private ListView lstDanhMuc;
    private Button btnThu, btnChi;
    private Spinner spinnerThang, spinnerNam;
    private TextView tvChiTieu, tvThuNhap, tvThuChi;
    private ArrayList<Class_GiaoDich> arrayList;
    private int year;
    private int checkThuChi = 1; //Phục vụ cho 2 button Tiền Thu và Tiền chi ẩn hiện
    //=1 là Thu, = 2 là Chi
    private ImageView imgTrangChu, imgLichSu, imgThongKe, imgDanhMuc;
    private FloatingActionButton btnTaoGiaoDich;

    private ArrayList<Class_GiaoDich> getAllGiaoDich;
    private ArrayList<Class_DanhMuc> getAllDanhMuc;
    private DBQuanLyChiTieu db;

    private int thangchon = 0, namchon = 0;

    private LocalDate localDate;

    private int buttonThuChi = 1;
    //Hàm onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        //Ánh xạ
        pieChart = findViewById(R.id.idpiechart);
        btnThu = findViewById(R.id.btnTienThu);
        btnChi = findViewById(R.id.btnTienChi);
        spinnerThang = findViewById(R.id.spinnerThang);
        spinnerNam = findViewById(R.id.spinnerNam);
        tvChiTieu = findViewById(R.id.tvChiTieu);
        tvThuNhap = findViewById(R.id.tvThuNhap);
        tvThuChi = findViewById(R.id.tvThuChi);
        lstDanhMuc = findViewById(R.id.listDanhMuc);

        imgTrangChu = findViewById(R.id.TrangChu);
        imgLichSu = findViewById(R.id.LichSuGiaoDich);
        imgThongKe = findViewById(R.id.ThongKe);
        imgDanhMuc = findViewById(R.id.DanhMuc);
        btnTaoGiaoDich = findViewById(R.id.TaoGiaoDich);


        //set giá trị cho tháng và năm ngay khi bắt đầu
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime now2 = LocalDateTime.now();
            thangchon = now2.getMonthValue();
            namchon = now2.getYear();
        }

        //Kết nối database
        db = new DBQuanLyChiTieu(this, "QuanLyDB", null, 12);

        //Lấy tất cả bản ghi của GiaoDich
        try {
            getAllGiaoDich = db.getAllGiaoDich();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        getAllDanhMuc = db.getAllDanhMuc();

        //Gắn tạm cho listview
        //arrayList = new ArrayList<>();

        //Gắn dữ liệu cho Spinner
        ArrayList<Integer> arrayThang = new ArrayList<>();
        ArrayList<Integer> arrayNam = new ArrayList<>();
        for(int i = 0; i < 12; i++)
        {
            arrayThang.add(i+1);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime now = LocalDateTime.now();
            year = now.getYear();
        }
        for(int i = year-2; i <= year; i++)
        {
            arrayNam.add(i);
        }
        //Gắn dữ liệu cho spinner Tháng
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayThang);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerThang.setAdapter(arrayAdapter);
        //Gắn dữ liệu cho spinner Năm
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayNam);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNam.setAdapter(arrayAdapter2);

        //Bắt đầu thì gán Spinner bằng hiện tại
        LocalDateTime now2 = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            now2 = LocalDateTime.now();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            spinnerThang.setSelection(arrayAdapter.getPosition(now2.getMonthValue()));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            spinnerNam.setSelection(arrayAdapter2.getPosition(now2.getYear()));
        }

        //Lấy tháng của Spinner
        spinnerThang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                thangchon = Integer.parseInt(spinnerThang.getSelectedItem().toString());
                //Toast.makeText(ThongKe.this, String.valueOf(thangchon), Toast.LENGTH_SHORT).show();

                setValue(thangchon, Integer.parseInt(spinnerNam.getSelectedItem().toString()));
                if(buttonThuChi == 1)
                {
                    setDataChart(thangchon, Integer.parseInt(spinnerNam.getSelectedItem().toString()), "Tiền thu", arrayList);
                    //setDataChart(2, 2023, "Tiền thu");
                }
                else
                {
                    setDataChart(thangchon, Integer.parseInt(spinnerNam.getSelectedItem().toString()), "Tiền chi", arrayList);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Lấy năm của Spinner
        spinnerNam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                namchon = Integer.parseInt(spinnerNam.getSelectedItem().toString());
                //Toast.makeText(ThongKe.this, String.valueOf(namchon), Toast.LENGTH_SHORT).show();

                setValue(Integer.parseInt(spinnerThang.getSelectedItem().toString()), namchon);

                if(buttonThuChi == 1)
                {
                    setDataChart(Integer.parseInt(spinnerThang.getSelectedItem().toString()), namchon, "Tiền thu", arrayList);
                }
                else
                {
                    setDataChart(Integer.parseInt(spinnerThang.getSelectedItem().toString()), namchon, "Tiền chi", arrayList);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Button Tiền thu và Tiền chi
        if(checkThuChi == 1)
        {
            btnChi.setBackgroundColor(Color.LTGRAY);
        }

        btnThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnThu.setBackgroundColor(Color.rgb(104, 4, 236));
                btnChi.setBackgroundColor(Color.LTGRAY);

                buttonThuChi = 1;

//                //Set Chart
//                setupPiechart("Tiền thu");
//
//                ArrayList<PieEntry> pieEntries = new ArrayList<>();
//                pieEntries.add(new PieEntry(500f, "Lương"));
//                pieEntries.add(new PieEntry(100f, "Thưởng"));
//                pieEntries.add(new PieEntry(300f, "Mẹ cho"));
//
//                loadPieChart(pieEntries);


                LocalDateTime now2 = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    now2 = LocalDateTime.now();
                }
                //Bắt đầu thì gán Spinner bằng hiện tại
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    spinnerThang.setSelection(arrayAdapter.getPosition(now2.getMonthValue()));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    spinnerNam.setSelection(arrayAdapter2.getPosition(now2.getYear()));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    setDataChart(now2.getMonthValue(), now2.getYear(), "Tiền thu", arrayList);
                }
            }
        });

        // Xử lý đổi giao diện mỗi khi bấm nút tiền chi
        btnChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnChi.setBackgroundColor(Color.rgb(104, 4, 236));
                btnThu.setBackgroundColor(Color.LTGRAY);

                buttonThuChi = 2;

                //Set Chart
//                setupPiechart("Tiền chi");
//
//                ArrayList<PieEntry> pieEntries = new ArrayList<>();
//                pieEntries.add(new PieEntry(147f, "Đi chơi"));
//                pieEntries.add(new PieEntry(400f, "Quần áo"));
//                pieEntries.add(new PieEntry(322f, "Ăn uống"));
//
//                loadPieChart(pieEntries);

                LocalDateTime now2 = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    now2 = LocalDateTime.now();
                }
                //Bắt đầu thì gán Spinner bằng hiện tại
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    spinnerThang.setSelection(arrayAdapter.getPosition(now2.getMonthValue()));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    spinnerNam.setSelection(arrayAdapter2.getPosition(now2.getYear()));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    setDataChart(now2.getMonthValue(), now2.getYear(), "Tiền chi", arrayList);
                }
            }
        });

        //Trang chủ
        imgTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(ThongKe.this, TrangChu.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 100);
                finish();
            }
        });

        //Lịch sử giao dịch
        imgLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(ThongKe.this, LichSuGiaoDich.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 200);
                finish();
            }
        });

        //Tạo giao dịch
        btnTaoGiaoDich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(ThongKe.this, NhapThuChi.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 300);
                finish();
            }
        });
        //Thống kê

        //Danh mục
        imgDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(ThongKe.this, DanhMuc.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 500);
                finish();
            }
        });

    }
    //Hết onCreate


    //Hàm để setValue
    public void setValue(int thang, int nam)
    {
        int tienchitieu = 0, tienthunhap = 0;
        //Set giá trị cho Chi tiêu và Thu nhập
        for(Class_GiaoDich gd: getAllGiaoDich)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                localDate = gd.getNgayGiaoDich().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if(gd.getLoaiGiaoDich().equals("Thu") && localDate.getMonthValue() == thang && localDate.getYear() == nam)
                {
                    tienthunhap += gd.getSoTienNhap();
                }
                if(gd.getLoaiGiaoDich().equals("Chi") && localDate.getMonthValue() == thang && localDate.getYear() == nam)
                {
                    tienchitieu += gd.getSoTienNhap();
                }
            }
            //localDate.getMonthValue() == Integer.parseInt(thangchon) && localDate.getYear() == Integer.parseInt(namchon)
        }

        //Set text cho Chi tiêu và Thu Nhập
        tvChiTieu.setText(String.valueOf(tienchitieu) + " đ");
        tvThuNhap.setText(String.valueOf(tienthunhap) + " đ");
    }

    public void setDataChart(int thang, int nam, String loaigiaodich, ArrayList<Class_GiaoDich> arrayList)
    {
        //Biểu đồ
        //Set Chart
        setupPiechart(loaigiaodich);

        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        arrayList = new ArrayList<>();
        arrayList.clear();
        adapter_listView_thongKe = new Adapter_ListView_ThongKe(arrayList, this);
        lstDanhMuc.setAdapter(adapter_listView_thongKe);

        //Thịnh viết ở đây
        int n=getAllDanhMuc.size();
        int m=getAllGiaoDich.size();
        int tongtienthu[] = new int[getAllDanhMuc.size()];
        int tongtienchi[] = new int[getAllDanhMuc.size()];
        if(loaigiaodich == "Tiền thu")
        {
            for(int i = 0; i < n; i++)
            {
                tongtienthu[i] = 0;
                for(int j = 0; j < m; j++)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        localDate = getAllGiaoDich.get(j).getNgayGiaoDich().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                    {
                        if (getAllDanhMuc.get(i).getTenDanhMuc().equals(getAllGiaoDich.get(j).getTenDanhMuc()) &&
                                getAllGiaoDich.get(j).getLoaiGiaoDich().equals("Thu") &&
                                localDate.getMonthValue() == thang && localDate.getYear() == nam)
                        {
                            tongtienthu[i] += getAllGiaoDich.get(j).getSoTienNhap();
                        }
                    }
                }
                if(tongtienthu[i] == 0)
                    continue;
                arrayList.add(new Class_GiaoDich(getAllDanhMuc.get(i).getTenDanhMuc(), tongtienthu[i]));
                pieEntries.add(new PieEntry(tongtienthu[i], getAllDanhMuc.get(i).getTenDanhMuc()));

                adapter_listView_thongKe = new Adapter_ListView_ThongKe(arrayList, this);
                lstDanhMuc.setAdapter(adapter_listView_thongKe);
            }
        }
        if(loaigiaodich == "Tiền chi")
        {
            for(int i = 0; i < n; i++)
            {
                tongtienchi[i] = 0;
                for(int j = 0; j < m; j++)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        localDate = getAllGiaoDich.get(j).getNgayGiaoDich().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                    {
                        if (getAllDanhMuc.get(i).getTenDanhMuc().equals(getAllGiaoDich.get(j).getTenDanhMuc()) &&
                                getAllGiaoDich.get(j).getLoaiGiaoDich().equals("Chi") &&
                                localDate.getMonthValue() == thang && localDate.getYear() == nam)
                        {
                            tongtienchi[i] += getAllGiaoDich.get(j).getSoTienNhap();
                        }
                    }
                }
                if(tongtienchi[i] == 0)
                    continue;
                arrayList.add(new Class_GiaoDich(getAllDanhMuc.get(i).getTenDanhMuc(), tongtienchi[i]));
                pieEntries.add(new PieEntry(tongtienchi[i], getAllDanhMuc.get(i).getTenDanhMuc()));

                adapter_listView_thongKe = new Adapter_ListView_ThongKe(arrayList, this);
                lstDanhMuc.setAdapter(adapter_listView_thongKe);
            }
        }

        //for(int i=0;i<3;i++)
            //Toast.makeText(this, getAllDanhMuc.get(i).getTenDanhMuc() + ":" + tongtienthu[i], Toast.LENGTH_SHORT).show();


        loadPieChart(pieEntries);
    }

    //Hàm tạo Biểu đồ tròn
    private void setupPiechart(String loai)
    {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText(loai);
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void loadPieChart(ArrayList<PieEntry> pieEntries)
    {
//        pieEntries = new ArrayList<>();
//        pieEntries.add(new PieEntry(300f, "Ăn uống"));
//        pieEntries.add(new PieEntry(100f, "Đi chơi"));
//        pieEntries.add(new PieEntry(257f, "Quần áo"));

        ArrayList<Integer> colors = new ArrayList<>();
        for(int color: ColorTemplate.MATERIAL_COLORS)
        {
            colors.add(color);
        }
        for(int color: ColorTemplate.VORDIPLOM_COLORS)
        {
            colors.add(color);
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, "Danh mục");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();
    }
}