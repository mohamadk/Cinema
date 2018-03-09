package com.mkhaleghy.cinema.detail;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mkhaleghy.cinema.R;
import com.mkhaleghy.cinema.detail.models.BookTime;

/**
 * Created by mk on 3/8/2018.
 */

public class TimeSpinnerAdapter extends ArrayAdapter<BookTime> {
    public TimeSpinnerAdapter(@NonNull Context context,@NonNull BookTime[] objects) {
        super(context, R.layout.item_book_time, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            v = inflater.inflate(R.layout.item_book_time, null);
        }
        TextView preTitle = v.findViewById(R.id.tv_preTitle);
        TextView title = v.findViewById(R.id.tv_title);

        preTitle.setText(getItem(position).prefixTitle());
        title.setText(getItem(position).title());

        return v;
    }
}
