package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

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

import java.util.ArrayList;
import java.util.List;

public class TrangChu extends AppCompatActivity {

//    AnyChartView anyChartView;
//    String[] months = {"Thang 1", "Thang 2", "Thang 3"};
//    int[] salary = {100, 200, 300};

    ArrayList barArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

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
    }

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