package com.mkhaleghy.cinema.detail.models;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mk on 3/8/2018.
 */
@AutoValue
public abstract class Detail implements Parcelable {
    @Nullable
    @SerializedName("tu")
    public abstract String thrillerUrl();

    @Nullable
    @SerializedName("c")
    public abstract String cover();

    @Nullable
    @SerializedName("i")
    public abstract String icon();


    @Nullable
    @SerializedName("ti")
    public abstract List<BookTime> bookTimes();


    @Nullable
    @SerializedName("t")
    public abstract String title();

    @Nullable
    @SerializedName("st")
    public abstract String subTitle();

    @SerializedName("r")
    public abstract float rate();

    @SerializedName("rt")
    public abstract String rateText();

    @Nullable
    @SerializedName("ip")
    public abstract List<Info> detailInfoPages();

    @Nullable
    @SerializedName("ipt")
    public abstract List<String> detailInfoPagesTitles();

    public static TypeAdapter<Detail> typeAdapter(Gson gson) {
        return new AutoValue_Detail.GsonTypeAdapter(gson);
    }
}
