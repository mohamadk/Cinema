package com.mkhaleghy.cinema.main;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.mkhaleghy.cinema.R;
import com.mkhaleghy.cinema.tools.RampImageView;
import com.mkhaleghy.cinema.tools.abstraction.Utils;

/**
 * Created by mk on 3/15/2018.
 */

public class RampLayoutBehavior extends CoordinatorLayout.Behavior<RampImageView> {
    public static final String TAG = "RampLayoutBehavior";
    int maximumScroll;

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
        Log.d(TAG, "onDependentViewChanged() called with: parent = [" + parent + "], child = [" + child + "], dependency = [" + dependency + "]"+" ty="+(dependency.getY()*1.5f+dependency.getBottom()));
        child.setTranslationY((int)(dependency.getY()*1.5f+dependency.getBottom()));
        return true;
    }

}
