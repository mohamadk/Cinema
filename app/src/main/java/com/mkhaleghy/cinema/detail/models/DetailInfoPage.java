package com.mkhaleghy.cinema.detail.models;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;


/**
 * Created by mk on 3/8/2018.
 */
@AutoValue
public abstract class DetailInfoPage {
    @Nullable
    @SerializedName("t")
    public abstract String title();

    @Nullable
    @SerializedName("i")
    public abstract Info info();

    public static TypeAdapter<DetailInfoPage> typeAdapter(Gson gson) {
        return new AutoValue_DetailInfoPage.GsonTypeAdapter(gson);
    }


}
