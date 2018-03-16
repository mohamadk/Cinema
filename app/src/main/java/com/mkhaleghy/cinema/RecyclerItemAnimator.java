package com.mkhaleghy.cinema;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mk on 3/2/2018.
 */

public class RecyclerItemAnimator {


    private LinearLayoutManager layoutManager;
    private float offset = .5f;

    public RecyclerItemAnimator(LinearLayoutManager layoutManager, float offset) {
        this.layoutManager = layoutManager;
        this.offset = offset;
    }

    View[] visibleItems;


    public void animate(float fraction) {
        if (visibleItems == null) {
            findVisibleItems();
        }

        float offsetStep = offset / visibleItems.length;
        for (int i = 0; i < visibleItems.length; i++) {
            View item = visibleItems[i];
            item.setTranslationX(i * offsetStep * item.getWidth() * fraction);
        }
    }

    private void findVisibleItems() {
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        visibleItems = new View[lastVisibleItemPosition - firstVisibleItemPosition + 1];

        for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
            visibleItems[i - firstVisibleItemPosition] = layoutManager.getChildAt(i);
        }
    }


}
