package com.mkhaleghy.cinema;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.mkhaleghy.cinema.main.MainPagerAdapter;

/**
 * Created by mk on 3/3/2018.
 */

public class ViewPagerTransform implements ViewPager.PageTransformer {
    public static final String TAG = "ViewPagerTransform";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private float offset = .5f;

    public ViewPagerTransform(float offset) {
        this.offset = offset;
    }

    View[] visibleItems;
    View mainView;

    public ViewPagerTransform() {
    }

    @Override
    public void transformPage(View view, float position) {
        Log.d(TAG, "transformPage() called with: position = [" + position + "]");
        if (position < -1) {
        } else if (position <= 1) { // [-1,1]
            if (mainView != view) {
                mainView = view;
                recyclerView = view.findViewById(R.id.rv_list);
                layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                findVisibleItems();
            }

            float offsetStep = offset / visibleItems.length;
            for (int i = 0; i < visibleItems.length; i++) {
                View item = visibleItems[i];
                if (item == null) {
                    continue;
                }
                int x=position<0?(visibleItems.length-i):i;
                item.setTranslationX(((x * offsetStep * item.getWidth() * (position))));
            }
        } else { // (1,+Infinity]
        }
    }

    private void findVisibleItems() {
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        if(firstVisibleItemPosition>0){
            firstVisibleItemPosition--;
        }
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        visibleItems = new View[lastVisibleItemPosition - firstVisibleItemPosition + 1];

        for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
            visibleItems[i - firstVisibleItemPosition] = layoutManager.getChildAt(i);
        }
    }


}
