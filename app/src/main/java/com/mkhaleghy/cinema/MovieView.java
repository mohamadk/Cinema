package com.mkhaleghy.cinema;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatRatingBar;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.mkhaleghy.cinema.adapter.Binder;

/**
 * Created by mk on 3/2/2018.
 */

public class MovieView extends ConstraintLayout implements Binder<Movie> {

    private ImageView iv_icon;
    private TextView tv_title;
    private TextView tv_subtitle;
    private TextView tv_genre;
    private TextView tv_time;
    private AppCompatRatingBar rb_rate;

    public MovieView(Context context) {
        super(context);
        init(null);
    }

    public MovieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MovieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attributeSet) {


    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        iv_icon = findViewById(R.id.iv_icon);
        tv_title = findViewById(R.id.tv_title);
        tv_subtitle = findViewById(R.id.tv_subtitle);
        tv_genre = findViewById(R.id.tv_genre);
        tv_time = findViewById(R.id.tv_time);
        rb_rate = findViewById(R.id.rb_rate);
    }

    @Override
    public void bind(Movie item) {
        iv_icon.setImageResource(R.drawable.kong);
        tv_title.setText(item.title);
        tv_subtitle.setText(item.subtitle);
        tv_genre.setText(item.genre);
        tv_time.setText(item.time);
        rb_rate.setRating(item.rate);
    }
}
