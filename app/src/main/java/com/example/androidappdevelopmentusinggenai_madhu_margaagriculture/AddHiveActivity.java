package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database.AppDatabase;
import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database.Hive;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executors;

public class AddHiveActivity extends AppCompatActivity {

    private EditText editTextHiveId, editTextLocation;
    private Button buttonSave;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hive);

        editTextHiveId = findViewById(R.id.editTextHiveId);
        editTextLocation = findViewById(R.id.editTextLocation);
        buttonSave = findViewById(R.id.buttonSaveHive);
        db = AppDatabase.getInstance(this);

        buttonSave.setOnClickListener(v -> saveHive());
    }

    private void saveHive() {
        String hiveId = editTextHiveId.getText().toString().trim();
        String location = editTextLocation.getText().toString().trim();

        if (hiveId.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        Hive hive = new Hive(hiveId, location, currentDate);

        // Run database operation on a background thread
        Executors.newSingleThreadExecutor().execute(() -> {
            db.hiveDao().insert(hive);
            runOnUiThread(() -> {
                Toast.makeText(this, "Hive saved successfully!", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }
}
