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
public abstract class BookTime implements Parcelable {
    @Nullable
    @SerializedName("pt")
    public abstract String prefixTitle();

    @SerializedName("t")
    public abstract String title();

    @SerializedName("i")
    public abstract int id();

    public static TypeAdapter<BookTime> typeAdapter(Gson gson) {
        return new AutoValue_BookTime.GsonTypeAdapter(gson);
    }

    @Override
    public String toString() {
        return title() + " " + prefixTitle();
    }
}
