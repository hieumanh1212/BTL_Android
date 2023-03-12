package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

public class TrangChu extends AppCompatActivity {

    AnyChartView anyChartView;
    String[] months = {"Thang 1", "Thang 2", "Thang 3"};
    int[] salary = {100, 200, 300};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        anyChartView = findViewById(R.id.anyChartView);

        setupChartView();
    }

    private void setupChartView()
    {
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

        for(int i = 0; i < months.length; i++)
        {
            dataEntries.add(new ValueDataEntry(months[i],salary[i]));
        }
        pie.data(dataEntries);
        pie.title(("Salary"));
        anyChartView.setChart(pie);
    }
}