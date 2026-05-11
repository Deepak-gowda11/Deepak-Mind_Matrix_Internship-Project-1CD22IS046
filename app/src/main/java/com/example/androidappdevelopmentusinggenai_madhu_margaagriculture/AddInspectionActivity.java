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

        // --- ENHANCED AI DECISION MATRIX ---
        StringBuilder alerts = new StringBuilder();

        // 1. Critical Health Alerts
        if (pestDetected) {
            alerts.append("🚨 CRITICAL: Pest detected! Treat hive with organic formic acid or check for mites immediately.\n");
        }
        if (!queenSeen) {
            alerts.append("⚠️ WARNING: Queen not spotted. Check for queen cells or eggs to confirm if the colony is queenless.\n");
        }

        // 2. Production & Harvest Intelligence
        if (honeyFlow.equals("High") && activityLevel.equals("High")) {
            alerts.append("✨ ACTION: Honey flow is peak! Prepare harvesting equipment. Consider adding a 'Super' box.\n");
        } else if (honeyFlow.equals("High")) {
            alerts.append("🍯 TIP: Honey stores are full. Harvest recommended within the next 3-5 days.\n");
        }

        // 3. Colony Growth & Splitting Logic
        if (activityLevel.equals("High") && queenSeen) {
            alerts.append("🐝 GROWTH: Colony is highly active and crowded. Good time to SPLIT THE COLONY to prevent swarming.\n");
        }

        // 4. Survival & Feeding Tips
        if (activityLevel.equals("Low") && honeyFlow.equals("Low")) {
            alerts.append("📉 ALERT: Low activity and low food. Provide sugar syrup (1:1) to support colony survival.\n");
        } else if (activityLevel.equals("Low")) {
            alerts.append("❓ NOTE: Unusual low activity. Check for weather impact or early disease symptoms.\n");
        }

        if (alerts.length() == 0) {
            alerts.append("✅ STATUS: Hive looks healthy and stable. Continue regular monitoring.");
        }

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
