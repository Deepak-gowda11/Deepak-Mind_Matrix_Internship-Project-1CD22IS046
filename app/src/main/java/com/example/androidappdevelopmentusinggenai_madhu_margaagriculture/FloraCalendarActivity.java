package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

public class FloraCalendarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flora_calendar);

        Toolbar toolbar = findViewById(R.id.toolbarFlora);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(v -> finish());
        }
    }
}
