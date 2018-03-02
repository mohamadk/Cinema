package com.mkhaleghy.cinema;

import com.mkhaleghy.cinema.adapter.Element;

/**
 * Created by mk on 3/2/2018.
 */

public class Movie implements Element {
    public static final int MOVIE=0;
    public int icon;
    public String title;
    public String subtitle;
    public float rate;
    public String genre;
    public String time;

    public Movie(int icon, String title, String subtitle,float rate, String genre, String date) {
        this.icon = icon;
        this.title = title;
        this.subtitle = subtitle;
        this.rate=rate;
        this.time = date;
        this.genre = genre;
    }

    @Override
    public int viewType() {
        return MOVIE;
    }
}
