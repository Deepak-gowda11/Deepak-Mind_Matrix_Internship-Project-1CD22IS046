package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HiveDao {
    @Insert
    void insert(Hive hive);

    @Query("SELECT * FROM hives ORDER BY id DESC")
    LiveData<List<Hive>> getAllHives();
}
