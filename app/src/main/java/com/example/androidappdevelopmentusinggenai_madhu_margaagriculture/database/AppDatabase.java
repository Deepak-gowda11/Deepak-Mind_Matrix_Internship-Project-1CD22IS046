package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Hive.class, Inspection.class, Harvest.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract HiveDao hiveDao();
    public abstract InspectionDao inspectionDao();
    public abstract HarvestDao harvestDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "madhu_marga_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
