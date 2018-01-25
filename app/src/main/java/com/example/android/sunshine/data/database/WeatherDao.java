package com.example.android.sunshine.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by magician on 1/17/2018.
 * query for DB
 */
@Dao
public interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(WeatherEntry... weather);

    @Query("SELECT * FROM weather WHERE date=:date")
    LiveData<WeatherEntry> getWeatherByDate(Date date);//update the return to wrapped LiveData

    @Query("SELECT id, weatherIconId, date, min, max FROM weather WHERE date >= :date")
    LiveData<List<ListViewWeatherEntry>>  getCurrentWeatherForecasts(Date date);

    @Query("SELECT COUNT(id) FROM weather WHERE date >=:date")
    int countAllFutureWeather(Date date);

    //Deletes any weather data older than the given day
    @Query("DELETE  FROM weather WHERE date <=:date")
    void deleteOldWeather(Date date);
}
