package com.example.android.sunshine.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

/**
 * Created by magic on 1/19/2018.
 * DB access
 */

@Database(entities = {WeatherEntry.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class SunshineDatabase extends RoomDatabase {
    private static final String LOG_TAG = SunshineDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "weather";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static volatile SunshineDatabase sInstance;

    public abstract WeatherDao weatherDao();//Getters for Dao


    /**
     * this method to create singleton object from DB
     *
     * @param context is used to get applicationContext
     **/
    public static SunshineDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        SunshineDatabase.class, SunshineDatabase.DATABASE_NAME).build();
                Log.d(LOG_TAG, "Create New DB Object");
            }
        }
        return sInstance;
    }


}
