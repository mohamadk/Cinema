package com.mkhaleghy.cinema.tools;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

/**
 * Created by mk on 3/9/2018.
 */

public class DraggableConstraintLayout extends ConstraintLayout {
    public static final String TAG = "DraggableConsLayout";
    ViewDragHelper mDragHelper;
    View mDragView;
    private View mDragViewCover;
    DragController mDragController;

    TimeInterpolator sAccelerator = new AccelerateInterpolator();
    float startX, startY, startTX, startTY;
    View releasedChild;

    public DraggableConstraintLayout(Context context) {
        super(context);
    }

    public DraggableConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DraggableConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {


        mDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {


            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                boolean res = mDragView==child;
                return res;
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);

                if (mDragController != null) {
                    mDragController.onDrag((int) (top-startY));
                }
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
                if (startX == 0) {
                    startX = capturedChild.getX();
                    startY = capturedChild.getY();
                    startTX = capturedChild.getTranslationX();
                    startTY = capturedChild.getTranslationY();
                }
                Log.d(TAG, "onViewCaptured: startX=" + startX + " startY=" + startY);

                if (mDragController != null) {
                    mDragController.onDragDrop(capturedChild, true);
                }


            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (mDragController != null) {
                    mDragController.onDragDrop(releasedChild, false);
                }
                releaseAnim(releasedChild, startX, startY);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getActionMasked();
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mDragHelper.cancel();
            return false;
        }
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    private void releaseAnim(final View releasedChild, float startX, float startY) {
        this.releasedChild = releasedChild;
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.X, releasedChild.getX(), startX);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.Y, releasedChild.getY(), startY);
        PropertyValuesHolder pvhTX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, releasedChild.getTranslationX(), startTX);
        PropertyValuesHolder pvhTY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, releasedChild.getTranslationY(), startTY);
        ObjectAnimator downAnim = ObjectAnimator.ofPropertyValuesHolder(
                releasedChild
                , pvhTX, pvhTY
                , pvhX, pvhY
        );

        downAnim.setInterpolator(sAccelerator);
        downAnim.setDuration(200);
        downAnim.start();

        downAnim.addUpdateListener(animation -> {
            if (mDragController != null) {
                mDragController.onDrag((int) (((float) animation.getAnimatedValue(View.Y.getName()))-startY));
            }
        });

        downAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                releasedChild.setVisibility(View.INVISIBLE);
                mDragViewCover.setVisibility(VISIBLE);
                requestLayout();
            }
        });


    }


    @Override
    public void requestLayout() {
        super.requestLayout();
        if (releasedChild != null && startX != 0) {
            Log.d(TAG, "requestLayout: ");
            releasedChild.post(() -> {
                mDragViewCover.setVisibility(INVISIBLE);
                releasedChild.setVisibility(VISIBLE);
                releasedChild.setX(startX);
                releasedChild.setY(startY);

                for (int i = 0; i < getChildCount(); i++) {
                    getChildAt(i).setTranslationY(0);
                    getChildAt(i).setTranslationX(0);
                }

            });

        }
    }

    public void addDragView(View dragView, View dragViewCover) {
        mDragView =dragView;
        mDragViewCover =dragViewCover;
    }


    public void setDragController(DragController mDragController) {
        this.mDragController = mDragController;
    }

    public interface DragController {
        public void onDragDrop(View view, boolean captured);
        public void onDrag(int dy);
    }
}
