package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.adapters.HiveAdapter;
import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database.AppDatabase;
import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database.Hive;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HiveAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerViewHives);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new HiveAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnHiveClickListener(new HiveAdapter.OnHiveClickListener() {
            @Override
            public void onInspectClick(Hive hive) {
                Intent intent = new Intent(MainActivity.this, InspectionListActivity.class);
                intent.putExtra("HIVE_ID", hive.getHiveId());
                startActivity(intent);
            }

            @Override
            public void onHarvestClick(Hive hive) {
                Intent intent = new Intent(MainActivity.this, HarvestListActivity.class);
                intent.putExtra("HIVE_ID", hive.getHiveId());
                startActivity(intent);
            }
        });

        db = AppDatabase.getInstance(this);
        db.hiveDao().getAllHives().observe(this, hives -> {
            adapter.setHives(hives);
        });

        FloatingActionButton fab = findViewById(R.id.fabAddHive);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddHiveActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_comparison) {
            startActivity(new Intent(this, HarvestComparisonActivity.class));
            return true;
        } else if (id == R.id.action_flora) {
            startActivity(new Intent(this, FloraCalendarActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
