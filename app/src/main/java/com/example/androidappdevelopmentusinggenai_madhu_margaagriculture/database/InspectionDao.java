package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InspectionDao {
    @Insert
    void insert(Inspection inspection);

    @Query("SELECT * FROM inspections WHERE hiveIdString = :hiveId ORDER BY id DESC")
    LiveData<List<Inspection>> getInspectionsForHive(String hiveId);
}
