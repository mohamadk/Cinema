package com.mkhaleghy.cinema.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.mkhaleghy.cinema.tools.RampImageView;
import com.mkhaleghy.cinema.tools.abstraction.Utils;

/**
 * Created by mk on 3/15/2018.
 */

public class RampLayoutBehavior extends CoordinatorLayout.Behavior<RampImageView> {
    public static final String TAG = "RampLayoutBehavior";


    public RampLayoutBehavior() {
    }

    public RampLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RampImageView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RampImageView child, View dependency) {

        Log.d(TAG, "onDependentViewChanged: dependency.getBottom()="+dependency.getBottom()
                +" dependency.getHeight()="+dependency.getHeight()
                +" dependency.getY()="+dependency.getY()
                +" dependency.getTop()="+dependency.getTop()
        );

        child.setTranslationY(dependency.getY()+dependency.getHeight());




        return true;
    }





}
