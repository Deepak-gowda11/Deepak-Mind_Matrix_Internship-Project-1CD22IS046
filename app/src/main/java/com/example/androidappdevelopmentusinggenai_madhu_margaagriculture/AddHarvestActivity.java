package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database.AppDatabase;
import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database.Harvest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executors;

public class AddHarvestActivity extends AppCompatActivity {

    private String hiveId;
    private EditText editTextQuantity;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_harvest);

        hiveId = getIntent().getStringExtra("HIVE_ID");
        editTextQuantity = findViewById(R.id.editTextQuantity);
        Button btnSave = findViewById(R.id.buttonSaveHarvest);

        db = AppDatabase.getInstance(this);
        btnSave.setOnClickListener(v -> saveHarvest());
    }

    private void saveHarvest() {
        String quantityStr = editTextQuantity.getText().toString().trim();
        if (quantityStr.isEmpty()) {
            Toast.makeText(this, "Enter quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        double quantity = Double.parseDouble(quantityStr);
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        Harvest harvest = new Harvest(hiveId, quantity, currentDate);

        Executors.newSingleThreadExecutor().execute(() -> {
            db.harvestDao().insert(harvest);
            runOnUiThread(() -> {
                Toast.makeText(this, "Harvest Logged", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }
}
