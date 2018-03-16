package com.mkhaleghy.cinema.detail;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mkhaleghy.cinema.R;

/**
 * Created by mk on 3/16/2018.
 */

public class SwitchSearchAnimHandler {
    public static final String TAG="SwitchSearchAnimHandler";
    private static final long DURATION = 1000;
    ImageView view;
    int switchSrc;
    AnimatorSet slideDownSet = new AnimatorSet();
    AnimatorSet slideUpSet = new AnimatorSet();


    public SwitchSearchAnimHandler(ImageView view, int switchSrc) {
        this.view = view;
        this.switchSrc = switchSrc;
        init();
    }

    private void init() {

        ObjectAnimator slideDown = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y,
                view.getContext().getResources().getDimension(R.dimen.fifty));

        slideDown.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d(TAG, "onAnimationUpdate: "+animation.getAnimatedFraction());
            }
        });


        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, View.ALPHA, 0);

        slideDownSet.play(slideDown).with(alpha);
        slideDownSet.setDuration(DURATION);
        slideDownSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setImageResource(switchSrc);
                slideUpSet.start();
            }
        });

        ObjectAnimator slideUp = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y,
                view.getContext().getResources().getDimension(R.dimen.fifty),0);
        ObjectAnimator alphaInc = ObjectAnimator.ofFloat(view, View.ALPHA, 1);

        slideUpSet.play(slideUp).with(alphaInc);
        slideUpSet.setDuration(DURATION);
        slideUpSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });

        slideDownSet.start();

    }


}
