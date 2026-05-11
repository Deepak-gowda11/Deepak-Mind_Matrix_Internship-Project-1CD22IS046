package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database.AppDatabase;
import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database.Inspection;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executors;

public class AddInspectionActivity extends AppCompatActivity {

    private String hiveId;
    private CheckBox checkQueen, checkPest;
    private Spinner spinnerHoney, spinnerActivity;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inspection);

        hiveId = getIntent().getStringExtra("HIVE_ID");
        checkQueen = findViewById(R.id.checkboxQueenSeen);
        checkPest = findViewById(R.id.checkboxPestDetected);
        spinnerHoney = findViewById(R.id.spinnerHoneyFlow);
        spinnerActivity = findViewById(R.id.spinnerActivityLevel);
        Button btnSave = findViewById(R.id.buttonSaveInspection);

        db = AppDatabase.getInstance(this);
        btnSave.setOnClickListener(v -> saveInspection());
    }

    private void saveInspection() {
        boolean queenSeen = checkQueen.isChecked();
        boolean pestDetected = checkPest.isChecked();
        String honeyFlow = spinnerHoney.getSelectedItem().toString();
        String activityLevel = spinnerActivity.getSelectedItem().toString();

        // AI Decision Matrix / Rule-based Alerts
        StringBuilder alerts = new StringBuilder();
        if (!queenSeen) alerts.append("Possible queen issue! ");
        if (pestDetected) alerts.append("Inspect hive immediately for pests! ");
        if (activityLevel.equals("Low")) alerts.append("Intervention Alert: Low activity! ");
        if (honeyFlow.equals("Low")) alerts.append("Consider supplemental feeding. ");

        String alertString = alerts.toString().trim();
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        Inspection inspection = new Inspection(hiveId, queenSeen, honeyFlow, activityLevel, pestDetected, currentDate, alertString);

        Executors.newSingleThreadExecutor().execute(() -> {
            db.inspectionDao().insert(inspection);
            runOnUiThread(() -> {
                Toast.makeText(this, "Inspection Logged", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }
}
