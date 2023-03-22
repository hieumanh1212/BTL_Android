package com.example.btl.activity.trangchu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl.database.DBQuanLyChiTieu;
import com.example.btl.R;
import com.example.btl.activity.danhmuc.DanhMuc;
import com.example.btl.activity.nhapthuchi.NhapThuChi;
import com.example.btl.activity.thongke.ThongKe;
import com.example.btl.activity.lichsugiaodich.LichSuGiaoDich;
import com.example.btl.models.Class_GiaoDich;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;

public class TrangChu extends AppCompatActivity {

//    AnyChartView anyChartView;
//    String[] months = {"Thang 1", "Thang 2", "Thang 3"};
//    int[] salary = {100, 200, 300};

    ArrayList barArrayList;
    private TextView tvTongSoDu, tvDanhMucGanNhat, tvNgayGanNhat, tvTienGanNhat;
    private ImageView imgTrangChu, imgLichSu, imgThongKe, imgDanhMuc;
    private FloatingActionButton btnTaoGiaoDich;

    private DBQuanLyChiTieu db;
    private static int TongTienThu = 0, TongTienChi = 0;
    private ArrayList<Class_GiaoDich> getAllGiaoDich;

    private LocalDate localDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);



        //Ánh xạ
        tvTongSoDu = findViewById(R.id.edTongSoDu);
        tvDanhMucGanNhat = findViewById(R.id.tvDanhMucGanNhat);
        tvNgayGanNhat = findViewById(R.id.tvThoiGianGanNhat);
        tvTienGanNhat = findViewById(R.id.tvTienGanNhat);

        imgTrangChu = findViewById(R.id.TrangChu);
        imgLichSu = findViewById(R.id.LichSuGiaoDich);
        imgThongKe = findViewById(R.id.ThongKe);
        imgDanhMuc = findViewById(R.id.DanhMuc);
        btnTaoGiaoDich = findViewById(R.id.TaoGiaoDich);

        //Kết nối database
        db = new DBQuanLyChiTieu(this, "DatabaseQuanLyChiTieu", null, 99);
        //Lấy tất cả bản ghi của GiaoDich
        try {
            getAllGiaoDich = db.getAllGiaoDich();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TongTienChi = TongTienThu = 0;
        //Lọc dữ liệu tách array thành thu ,chi
        for(Class_GiaoDich gd: getAllGiaoDich){
            if(gd.getLoaiGiaoDich().equals("Thu"))
            {
                TongTienThu += gd.getSoTienNhap();
            }
            if(gd.getLoaiGiaoDich().equals("Chi"))
            {
                TongTienThu -= gd.getSoTienNhap();
            }
        }
        //Set text cho Tổng số dư
        tvTongSoDu.setText("Tổng số dư: " + String.valueOf(TongTienThu-TongTienChi) + " đ");


        //Biểu đồ
        int tienthangtruoc = 0, tienthangnay = 0;
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;

        //Lấy tiền tháng này và tiền tháng trước ---- TIỀN CHI
        for(Class_GiaoDich gd: getAllGiaoDich){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                localDate = gd.getNgayGiaoDich().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if(gd.getLoaiGiaoDich().equals("Chi") && localDate.getMonthValue() == currentMonth-1)
                {
                    tienthangtruoc += gd.getSoTienNhap();
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if(gd.getLoaiGiaoDich().equals("Chi") && localDate.getMonthValue() == currentMonth)
                {
                    tienthangnay += gd.getSoTienNhap();
                }
            }
        }

//        Toast.makeText(this, tienthangtruoc + " va " + tienthangnay, Toast.LENGTH_SHORT).show();
//        anyChartView = findViewById(R.id.anyChartView);
//
//        setupChartView();
        BarChart barChart = findViewById(R.id.idbarchart);
        getData(tienthangtruoc,tienthangnay);
        BarDataSet barDataSet = new BarDataSet(barArrayList, " ");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(true);


        //Giao dịch gần nhất
        LocalDate localdategannhat = null;
        try {
            tvDanhMucGanNhat.setText(getAllGiaoDich.get(getAllGiaoDich.size()-1).getTenDanhMuc().toString());
            if(getAllGiaoDich.get(getAllGiaoDich.size()-1).getLoaiGiaoDich().contains("Thu"))
            {
                tvTienGanNhat.setText("+ " + getAllGiaoDich.get(getAllGiaoDich.size()-1).getSoTienNhap());
                //Toast.makeText(this, "Hello Thu", Toast.LENGTH_SHORT).show();
            }
            if(getAllGiaoDich.get(getAllGiaoDich.size()-1).getLoaiGiaoDich().contains("Chi"))
            {
                tvTienGanNhat.setText("- " + getAllGiaoDich.get(getAllGiaoDich.size()-1).getSoTienNhap());
                //Toast.makeText(this, "Hello Chi", Toast.LENGTH_SHORT).show();
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                localdategannhat = getAllGiaoDich.get(getAllGiaoDich.size()-1).getNgayGiaoDich().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                tvNgayGanNhat.setText("Ngày " + localdategannhat.getDayOfMonth() + " Tháng " + localdategannhat.getMonthValue() + " Năm " + localdategannhat.getYear());
            }
        } catch (Exception e){

        }


        //MENU
        //Trang chủ

        //Lịch sử giao dịch
        imgLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(TrangChu.this, LichSuGiaoDich.class);
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
                Intent intent = new Intent(TrangChu.this, NhapThuChi.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 300);
                finish();
            }
        });
        //Thống kê
        imgThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(TrangChu.this, ThongKe.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 400);
                finish();
            }
        });
        //Danh mục
        imgDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(TrangChu.this, DanhMuc.class);
                //2. Truyền dữ liệu sang subactivity bằng bundle nếu cần
                //3. Mở subactivity bằng cách gọi hàm startactivity hoặc startactivityforresult
                startActivityForResult(intent, 500);

            }
        });
    }


    //Hết onCreate

    private void getData(int x, int y)
    {
        barArrayList = new ArrayList();
        barArrayList.add(new BarEntry(1f, x));
        barArrayList.add(new BarEntry(2f, y));
    }
}