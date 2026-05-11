package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.adapters.HarvestAdapter;
import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HarvestListActivity extends AppCompatActivity {

    private String hiveId;
    private RecyclerView recyclerView;
    private HarvestAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harvest_list);

        hiveId = getIntent().getStringExtra("HIVE_ID");

        Toolbar toolbar = findViewById(R.id.toolbarHarvest);
        toolbar.setTitle("Harvests: " + hiveId);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerViewHarvests);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HarvestAdapter();
        recyclerView.setAdapter(adapter);

        db = AppDatabase.getInstance(this);
        db.harvestDao().getHarvestsForHive(hiveId).observe(this, harvests -> {
            adapter.setHarvests(harvests);
        });

        FloatingActionButton fab = findViewById(R.id.fabAddHarvest);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(HarvestListActivity.this, AddHarvestActivity.class);
            intent.putExtra("HIVE_ID", hiveId);
            startActivity(intent);
        });
    }
}
