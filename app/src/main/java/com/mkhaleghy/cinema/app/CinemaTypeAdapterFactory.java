package com.mkhaleghy.cinema.app;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by mk on 3/8/2018.
 */
@GsonTypeAdapterFactory
public abstract class CinemaTypeAdapterFactory implements TypeAdapterFactory {
    // Static factory method to access the package
    // private generated implementation
    public static TypeAdapterFactory create() {
        return new AutoValueGson_CinemaTypeAdapterFactory();
    }

}
