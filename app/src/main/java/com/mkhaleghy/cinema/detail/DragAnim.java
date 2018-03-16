package com.mkhaleghy.cinema.detail;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.support.v4.widget.ViewDragHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import java.util.ArrayList;

import static android.view.MotionEvent.INVALID_POINTER_ID;

/**
 * Created by mk on 3/8/2018.
 */

public class DragAnim {
    public static final String TAG = "DragAnim";
    // The ‘active pointer’ is the one currently moving our object.
    private int mActivePointerId = INVALID_POINTER_ID;

    ViewGroup parent;
    private ViewDragHelper mDragHelper;
    private ArrayList<View> mDragViews;
    private DragFrameLayoutController mDragFrameLayoutController;
    private TimeInterpolator sAccelerator = new AccelerateInterpolator();

    public DragAnim(ViewGroup view, DragFrameLayoutController mDragFrameLayoutController) {
        this.parent = view;
        this.mDragFrameLayoutController = mDragFrameLayoutController;
        animate();

    }

    private void animate() {
        mDragViews = new ArrayList<>();
        mDragHelper = ViewDragHelper.create(parent, 1.0f, new ViewDragHelper.Callback() {
            float startX, startY;

            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return mDragViews.contains(child);
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }

            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
                startX = capturedChild.getX();
                startY = capturedChild.getY();

                if (mDragFrameLayoutController != null) {
                    mDragFrameLayoutController.onDragDrop(capturedChild, true);
                }
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (mDragFrameLayoutController != null) {
                    mDragFrameLayoutController.onDragDrop(releasedChild, false);
                }
                releaseAnim(releasedChild, startX, startY);
            }
        });


        parent.setOnTouchListener((v, event) -> {
            Log.d(TAG, "aanimate: OnTouchListener: ");
            mDragHelper.processTouchEvent(event);
            return true;
        });

    }

    private void releaseAnim(View releasedChild, float startX, float startY) {
        Log.d(TAG, "releaseAnim() called with: releasedChild = [" + releasedChild + "], startX = [" + startX + "], startY = [" + startY + "]");
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.X,
                startX);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.Y, startY);
        ObjectAnimator downAnim = ObjectAnimator.ofPropertyValuesHolder(
                releasedChild, pvhX, pvhY);

        downAnim.setInterpolator(sAccelerator);
        downAnim.setDuration(200);
        downAnim.start();


    }

    public void addDragView(View dragView) {
        mDragViews.add(dragView);
    }

    public interface DragFrameLayoutController {

        public void onDragDrop(View view, boolean captured);
    }
}
