package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TrangChu extends AppCompatActivity {

//    AnyChartView anyChartView;
//    String[] months = {"Thang 1", "Thang 2", "Thang 3"};
//    int[] salary = {100, 200, 300};

    ArrayList barArrayList;
    private TextView tvTongSoDu, tvDanhMucGanNhat, tvNgayGanNhat, tvTienGanNhat;
    private ImageView imgTrangChu, imgLichSu, imgThongKe, imgDanhMuc;
    private FloatingActionButton btnTaoGiaoDich;
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


//        anyChartView = findViewById(R.id.anyChartView);
//
//        setupChartView();
        BarChart barChart = findViewById(R.id.idbarchart);
        getData();
        BarDataSet barDataSet = new BarDataSet(barArrayList, " ");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(true);

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

    private void getData()
    {
        barArrayList = new ArrayList();
        barArrayList.add(new BarEntry(1f, 50));
        barArrayList.add(new BarEntry(2f, 20));
    }
//    private void setupChartView()
//    {
//        Pie pie = AnyChart.pie();
//
//        List<DataEntry> dataEntries = new ArrayList<>();
//
//        for(int i = 0; i < months.length; i++)
//        {
//            dataEntries.add(new ValueDataEntry(months[i],salary[i]));
//        }
//        pie.data(dataEntries);
//        pie.title(("Tiền Chi"));
//        anyChartView.setChart(pie);
//    }
}