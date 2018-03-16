package com.mkhaleghy.cinema.adapter;

/**
 * Created by mk on 3/1/2018.
 */

public interface Binder <T extends Element> {
    void bind(T item, RecyclerAdapter.OnAdapterInteractionListener mListener);
}
