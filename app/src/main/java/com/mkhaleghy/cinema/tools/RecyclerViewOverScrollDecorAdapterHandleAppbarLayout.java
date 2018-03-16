package com.mkhaleghy.cinema.tools;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter;

/**
 * Created by mk on 3/16/2018.
 */

public class RecyclerViewOverScrollDecorAdapterHandleAppbarLayout extends RecyclerViewOverScrollDecorAdapter implements AppBarLayout.OnOffsetChangedListener {
    public static final String TAG = "HandleAppbarLayout";
    AppBarLayout appBarLayout;
    private boolean fullyExpanded = false;

    public RecyclerViewOverScrollDecorAdapterHandleAppbarLayout(RecyclerView recyclerView) {
        super(recyclerView);
    }

    public RecyclerViewOverScrollDecorAdapterHandleAppbarLayout(AppBarLayout appBarLayout, RecyclerView recyclerView) {
        super(recyclerView);
        this.appBarLayout = appBarLayout;

        appBarLayout.addOnOffsetChangedListener(this);
    }

    public RecyclerViewOverScrollDecorAdapterHandleAppbarLayout(RecyclerView recyclerView, Impl impl) {
        super(recyclerView, impl);
    }

    public RecyclerViewOverScrollDecorAdapterHandleAppbarLayout(RecyclerView recyclerView, ItemTouchHelper.Callback itemTouchHelperCallback) {
        super(recyclerView, itemTouchHelperCallback);
    }

    public RecyclerViewOverScrollDecorAdapterHandleAppbarLayout(RecyclerView recyclerView, Impl impl, ItemTouchHelper.Callback itemTouchHelperCallback) {
        super(recyclerView, impl, itemTouchHelperCallback);
    }


    @Override
    public boolean isInAbsoluteStart() {
        return super.isInAbsoluteStart() && fullyExpanded;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        if (verticalOffset == 0) {
            fullyExpanded = true;
        } else {
            fullyExpanded = false;
        }
    }


    public boolean isFullyExpanded() {
        return fullyExpanded;
    }
}
