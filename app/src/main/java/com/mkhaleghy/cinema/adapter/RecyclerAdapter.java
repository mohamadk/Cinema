package com.mkhaleghy.cinema.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mkhaleghy.cinema.R;
import com.mkhaleghy.cinema.daylist.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mk on 3/1/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    List<Movie> items=new ArrayList<>();
    LayoutInflater inflater;

    public RecyclerAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_movie, parent,false));
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).viewType();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public void bindItems(List<Movie> movies) {
        items = movies;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        public void bind(Element item) {
            ((Binder) itemView).bind(item);
        }
    }
}
