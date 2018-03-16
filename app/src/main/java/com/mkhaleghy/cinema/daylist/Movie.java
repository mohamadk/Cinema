package com.mkhaleghy.cinema.daylist;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.mkhaleghy.cinema.adapter.Element;
import com.mkhaleghy.cinema.detail.models.Detail;

/**
 * Created by mk on 3/2/2018.
 */
@AutoValue
public abstract class Movie implements Element ,Parcelable{
    public static final int MOVIE=0;

    @SerializedName("i")
    public abstract String id();

    @SerializedName("ic")
    public abstract String icon();

    @SerializedName("t")
    public abstract String title();

    @SerializedName("st")
    public abstract String subtitle();

    @SerializedName("r")
    public abstract float rate();

    @SerializedName("g")
    public abstract String genre();

    @SerializedName("ti")
    public abstract String time();

    @SerializedName("d")
    public abstract Detail detail();

    @Override
    public int viewType() {
        return MOVIE;
    }


    public static TypeAdapter<Movie> typeAdapter(Gson gson) {
        return new AutoValue_Movie.GsonTypeAdapter(gson);
    }

}
