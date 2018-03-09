package com.mkhaleghy.cinema.detail;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by mk on 3/9/2018.
 */

public class DragBackHandler {

    int maxDrag;

    ArrayList<View> viewsToScroll=new ArrayList<>();

    public void drag(int dy) {
        if(dy>0 && dy<=maxDrag){
            for (View view : viewsToScroll) {
                view.setTranslationY(dy);
            }
        }
    }

    public void addViewToScroll(View view){
        viewsToScroll.add(view);
    }


}
