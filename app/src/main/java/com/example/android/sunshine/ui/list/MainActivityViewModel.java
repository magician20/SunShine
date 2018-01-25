package com.example.android.sunshine.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.sunshine.data.SunshineRepository;
import com.example.android.sunshine.data.database.WeatherEntry;

import java.util.Date;
import java.util.List;

/**
 * Created by magic on 1/25/2018.
 *
 */

public class MainActivityViewModel extends ViewModel {
    private final LiveData<List<WeatherEntry>> mWeather;
    private final SunshineRepository mRepository;

    public MainActivityViewModel(SunshineRepository repository) {
        mRepository = repository;
        mWeather = mRepository.getCurrentWeatherForecasts();
    }


    public LiveData<List<WeatherEntry>> getWeather() {
        return mWeather;
    }
}
