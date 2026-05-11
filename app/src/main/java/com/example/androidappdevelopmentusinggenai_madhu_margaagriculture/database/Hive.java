package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "hives")
public class Hive {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String hiveId; // User defined ID like "Hive-01"
    private String location;
    private String dateRegistered;

    public Hive(String hiveId, String location, String dateRegistered) {
        this.hiveId = hiveId;
        this.location = location;
        this.dateRegistered = dateRegistered;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getHiveId() { return hiveId; }
    public void setHiveId(String hiveId) { this.hiveId = hiveId; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDateRegistered() { return dateRegistered; }
    public void setDateRegistered(String dateRegistered) { this.dateRegistered = dateRegistered; }
}
