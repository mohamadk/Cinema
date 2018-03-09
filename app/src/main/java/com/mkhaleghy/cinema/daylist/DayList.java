package com.mkhaleghy.cinema.daylist;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mk on 3/8/2018.
 */
@AutoValue
public abstract class DayList {

    @SerializedName("m")
    public abstract List<Movie> movies();


    public static TypeAdapter<DayList> typeAdapter(Gson gson) {
        return new AutoValue_DayList.GsonTypeAdapter(gson);
    }

}
