package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database.AppDatabase;
import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database.Harvest;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HarvestComparisonActivity extends AppCompatActivity {

    private BarChart barChart;
    private PieChart pieChart;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harvest_comparison);

        Toolbar toolbar = findViewById(R.id.toolbarComparison);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(v -> finish());
        }

        barChart = findViewById(R.id.barChart);
        pieChart = findViewById(R.id.pieChart);
        db = AppDatabase.getInstance(this);

        db.harvestDao().getAllHarvests().observe(this, harvests -> {
            if (harvests != null && !harvests.isEmpty()) {
                updateBarChart(harvests);
                updatePieChart(harvests);
            }
        });
    }

    private void updateBarChart(List<Harvest> harvests) {
        Map<String, Double> yearlyData = new HashMap<>();
        for (Harvest h : harvests) {
            String date = h.getDate();
            if (date != null && date.contains("-")) {
                String year = date.substring(date.lastIndexOf("-") + 1);
                yearlyData.put(year, yearlyData.getOrDefault(year, 0.0) + h.getQuantity());
            }
        }

        List<BarEntry> entries = new ArrayList<>();
        int i = 0;
        for (Map.Entry<String, Double> entry : yearlyData.entrySet()) {
            entries.add(new BarEntry(i++, entry.getValue().floatValue()));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Total Harvest (Kg) per Year");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(12f);

        BarData data = new BarData(dataSet);
        barChart.setData(data);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setTextColor(Color.BLACK);
        barChart.getXAxis().setTextColor(Color.BLACK);
        barChart.getAxisLeft().setTextColor(Color.BLACK);
        barChart.getAxisRight().setTextColor(Color.BLACK);
        
        barChart.animateY(1000);
        barChart.invalidate();
    }

    private void updatePieChart(List<Harvest> harvests) {
        Map<String, Double> hiveData = new HashMap<>();
        for (Harvest h : harvests) {
            hiveData.put(h.getHiveIdString(), hiveData.getOrDefault(h.getHiveIdString(), 0.0) + h.getQuantity());
        }

        List<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<String, Double> entry : hiveData.entrySet()) {
            entries.add(new PieEntry(entry.getValue().floatValue(), entry.getKey()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Harvest by Hive");
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(12f);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Hive Yield %");
        pieChart.setCenterTextColor(Color.BLACK);
        pieChart.getLegend().setTextColor(Color.BLACK);
        pieChart.setEntryLabelColor(Color.BLACK);

        pieChart.animateXY(1000, 1000);
        pieChart.invalidate();
    }
}
