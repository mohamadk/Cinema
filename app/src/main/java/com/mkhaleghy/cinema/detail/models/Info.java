package com.mkhaleghy.cinema.detail.models;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;


/**
 * Created by mk on 3/8/2018.
 */
@AutoValue
public abstract class Info implements Parcelable {
    @Nullable
    @SerializedName("t")
    public abstract String title();

    @Nullable
    @SerializedName("c")
    public abstract String content();

    public static TypeAdapter<Info> typeAdapter(Gson gson) {
        return new AutoValue_Info.GsonTypeAdapter(gson);
    }


}
