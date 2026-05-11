package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "inspections")
public class Inspection {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String hiveIdString; // Linked to Hive.hiveId
    private boolean queenSeen;
    private String honeyFlow; // Low, Medium, High
    private String activityLevel; // Low, Normal, High
    private boolean pestDetected;
    private String date;
    private String alert;

    public Inspection(String hiveIdString, boolean queenSeen, String honeyFlow, String activityLevel, boolean pestDetected, String date, String alert) {
        this.hiveIdString = hiveIdString;
        this.queenSeen = queenSeen;
        this.honeyFlow = honeyFlow;
        this.activityLevel = activityLevel;
        this.pestDetected = pestDetected;
        this.date = date;
        this.alert = alert;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getHiveIdString() { return hiveIdString; }
    public void setHiveIdString(String hiveIdString) { this.hiveIdString = hiveIdString; }
    public boolean isQueenSeen() { return queenSeen; }
    public void setQueenSeen(boolean queenSeen) { this.queenSeen = queenSeen; }
    public String getHoneyFlow() { return honeyFlow; }
    public void setHoneyFlow(String honeyFlow) { this.honeyFlow = honeyFlow; }
    public String getActivityLevel() { return activityLevel; }
    public void setActivityLevel(String activityLevel) { this.activityLevel = activityLevel; }
    public boolean isPestDetected() { return pestDetected; }
    public void setPestDetected(boolean pestDetected) { this.pestDetected = pestDetected; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getAlert() { return alert; }
    public void setAlert(String alert) { this.alert = alert; }
}
