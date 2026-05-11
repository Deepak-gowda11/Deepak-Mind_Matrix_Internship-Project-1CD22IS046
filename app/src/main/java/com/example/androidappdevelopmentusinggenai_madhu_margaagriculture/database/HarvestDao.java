package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HarvestDao {
    @Insert
    void insert(Harvest harvest);

    @Query("SELECT * FROM harvests ORDER BY id DESC")
    LiveData<List<Harvest>> getAllHarvests();

    @Query("SELECT * FROM harvests WHERE hiveIdString = :hiveId ORDER BY id DESC")
    LiveData<List<Harvest>> getHarvestsForHive(String hiveId);
}
