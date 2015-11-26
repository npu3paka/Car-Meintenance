package com.example.kosyo.carserviceproject;

import android.app.Application;
import android.content.Context;

/**
 * Created by kosyo on 26.11.15.
 */
public class CarServiceApplication extends Application{

    public static Context mainContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mainContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mainContext;
    }
}
