package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

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

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

public class ThongKe extends AppCompatActivity {

    private PieChart pieChart;
    private Adapter_ListView_ThongKe adapter_listView_thongKe;
    private ListView lstDanhMuc;
    private Button btnThu, btnChi;
    private Spinner spinnerThang, spinnerNam;
    private TextView tvChiTieu, tvThuNhap, tvThuChi;
    private ArrayList<GiaoDich> arrayList;
    private int year;
    private int checkThuChi = 1; //Phục vụ cho 2 button Tiền Thu và Tiền chi ẩn hiện
    //=1 là Thu, = 2 là Chi
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

        //Set Chart
        setupPiechart("Tiền thu");

        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(500f, "Lương"));
        pieEntries.add(new PieEntry(100f, "Thưởng"));
        pieEntries.add(new PieEntry(300f, "Mẹ cho"));

        loadPieChart(pieEntries);

        //Gắn tạm cho listview
        arrayList = new ArrayList<GiaoDich>();
        arrayList.add(new GiaoDich("Ăn uống", 100));
        arrayList.add(new GiaoDich("Đi chơi", 100));


        adapter_listView_thongKe = new Adapter_ListView_ThongKe(arrayList, this);
        lstDanhMuc.setAdapter(adapter_listView_thongKe);

        //Gắn dữ liệu cho Spinner
        ArrayList<Integer> arrayThang = new ArrayList<>();
        ArrayList<Integer> arrayNam = new ArrayList<>();
        for(int i = 0; i < 12; i++)
        {
            arrayThang.add(i+1);
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
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

                //Set Chart
                setupPiechart("Tiền thu");

                ArrayList<PieEntry> pieEntries = new ArrayList<>();
                pieEntries.add(new PieEntry(500f, "Lương"));
                pieEntries.add(new PieEntry(100f, "Thưởng"));
                pieEntries.add(new PieEntry(300f, "Mẹ cho"));

                loadPieChart(pieEntries);
            }
        });

        // Xử lý đổi giao diện mỗi khi bấm nút tiền chi
        btnChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnChi.setBackgroundColor(Color.rgb(104, 4, 236));
                btnThu.setBackgroundColor(Color.LTGRAY);

                //Set Chart
                setupPiechart("Tiền chi");

                ArrayList<PieEntry> pieEntries = new ArrayList<>();
                pieEntries.add(new PieEntry(147f, "Đi chơi"));
                pieEntries.add(new PieEntry(400f, "Quần áo"));
                pieEntries.add(new PieEntry(322f, "Ăn uống"));

                loadPieChart(pieEntries);
            }
        });

    }
    //Hết onCreate

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