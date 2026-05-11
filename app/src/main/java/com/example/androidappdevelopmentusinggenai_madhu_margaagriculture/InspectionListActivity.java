package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.adapters.InspectionAdapter;
import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InspectionListActivity extends AppCompatActivity {

    private String hiveId;
    private RecyclerView recyclerView;
    private InspectionAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_list);

        hiveId = getIntent().getStringExtra("HIVE_ID");

        Toolbar toolbar = findViewById(R.id.toolbarInspection);
        toolbar.setTitle("Inspections: " + hiveId);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerViewInspections);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InspectionAdapter();
        recyclerView.setAdapter(adapter);

        db = AppDatabase.getInstance(this);
        db.inspectionDao().getInspectionsForHive(hiveId).observe(this, inspections -> {
            adapter.setInspections(inspections);
        });

        FloatingActionButton fab = findViewById(R.id.fabAddInspection);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(InspectionListActivity.this, AddInspectionActivity.class);
            intent.putExtra("HIVE_ID", hiveId);
            startActivity(intent);
        });
    }
}
