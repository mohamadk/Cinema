package com.mkhaleghy.cinema.app;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mkhaleghy.cinema.adapter.Element;

/**
 * Created by mk on 3/8/2018.
 */

public class Ci extends Application {
    public static Gson gson;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;

        gson = new GsonBuilder()
                .registerTypeAdapterFactory(CinemaTypeAdapterFactory.create())
                .create();
    }

    public static Context context() {
        return context;
    }
}
