package com.example.android.sunshine.utilities;

import android.content.Context;

import com.example.android.sunshine.AppExecutors;
import com.example.android.sunshine.data.SunshineRepository;
import com.example.android.sunshine.data.database.SunshineDatabase;
import com.example.android.sunshine.data.network.WeatherNetworkDataSource;
import com.example.android.sunshine.ui.detail.DetailViewModelFactory;
import com.example.android.sunshine.ui.list.MainViewModelFactory;

import java.util.Date;

/**
 * Provides static methods to inject the various classes needed for Sunshine
 */
public class InjectorUtils {

    public static SunshineRepository provideRepository(Context context) {
        SunshineDatabase database = SunshineDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        WeatherNetworkDataSource networkDataSource =
                WeatherNetworkDataSource.getInstance(context.getApplicationContext(), executors);
        return SunshineRepository.getInstance(database.weatherDao(), networkDataSource, executors);
    }

    //Issue with Repository not existing when the app is gone - need to provide
    //Modify the injector method to make sure repo dependency is there
    public static WeatherNetworkDataSource provideNetworkDataSource(Context context) {
        // This call to provide repository is necessary if the app starts from a service - in this
       // case the repository will not exist unless it is specifically created.
        provideRepository(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        return WeatherNetworkDataSource.getInstance(context.getApplicationContext(), executors);
    }

    public static DetailViewModelFactory provideDetailViewModelFactory(Context context, Date date) {
        SunshineRepository repository = provideRepository(context.getApplicationContext());
        return new DetailViewModelFactory(repository, date);
    }

    public static MainViewModelFactory provideMainActivityViewModelFactory(Context context) {
        SunshineRepository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(repository);
    }

}