package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "harvests")
public class Harvest {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String hiveIdString;
    private double quantity; // in kg
    private String date;

    public Harvest(String hiveIdString, double quantity, String date) {
        this.hiveIdString = hiveIdString;
        this.quantity = quantity;
        this.date = date;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getHiveIdString() { return hiveIdString; }
    public void setHiveIdString(String hiveIdString) { this.hiveIdString = hiveIdString; }
    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
