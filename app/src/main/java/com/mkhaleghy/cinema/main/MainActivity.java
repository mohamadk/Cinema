package com.mkhaleghy.cinema.main;

import android.app.SharedElementCallback;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.mkhaleghy.cinema.BaseActivity;
import com.mkhaleghy.cinema.R;
import com.mkhaleghy.cinema.ViewPagerTransform;
import com.mkhaleghy.cinema.daylist.DayListFragment;
import com.mkhaleghy.cinema.main.MainPagerAdapter;
import com.mkhaleghy.cinema.tools.RampImageView;
import com.mkhaleghy.cinema.tools.SlidingTabLayout;
import com.mkhaleghy.cinema.tools.abstraction.OnPageChangeListener;
import com.mkhaleghy.cinema.tools.abstraction.Utils;

import java.util.List;

public class MainActivity extends BaseActivity implements DayListFragment.OnFragmentInteractionListener {
    public static final String TAG = "MainActivity";
    private ViewPager vp_pager;
    private SlidingTabLayout stl_tabs;
    private AppBarLayout apl_appbar;
    private RampImageView riv_ramp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        final MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), 30);
        vp_pager.setAdapter(pagerAdapter);
        vp_pager.setPageTransformer(true, new ViewPagerTransform());

        stl_tabs.setSelectedIndicatorColors(Color.TRANSPARENT);
        stl_tabs.setSelectedScaleFactor(.8f);
        stl_tabs.setViewPager(vp_pager);

        toolbar.setNavigationOnClickListener(v -> {
            if (materialMenu.getIconState() == MaterialMenuDrawable.IconState.ARROW) {
                materialMenu.animateIconState(MaterialMenuDrawable.IconState.BURGER);
            } else {
                materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW);
            }
        });
        toolbar.setTitle("");

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initViews() {

        vp_pager = findViewById(R.id.vp_pager);
        stl_tabs = findViewById(R.id.stl_tabs);
        apl_appbar = findViewById(R.id.apl_appbar);
        riv_ramp = findViewById(R.id.riv_ramp);
        apl_appbar.setPadding(apl_appbar.getPaddingLeft(), Utils.getStatusBarHeight(), apl_appbar.getPaddingRight(), apl_appbar.getPaddingBottom());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setExitSharedElementCallback(new SharedElementCallback() {
                @Override
                public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                    super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
                    materialMenu.animateIconState(MaterialMenuDrawable.IconState.BURGER);
                }
            });
        }

    }

    @Override
    public AppBarLayout appbarLayout() {
        return apl_appbar;
    }

    @Override
    public void stretch(float offset, int offsetInPx) {
        Log.d(TAG, "stretch() called with: offset = [" + offset + "], offsetInPx = [" + offsetInPx + "] trans=" + ((offset - 1) * (apl_appbar.getHeight() / 2)));
        apl_appbar.setTranslationY((offset - 1) * (apl_appbar.getHeight() / 2));
        apl_appbar.setScaleY(offset);

        riv_ramp.setRampDy(-offsetInPx);// -offsetInPx "-" because riv_ramp rotated 180
    }

    @Override
    public void detailSelected() {
        materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW);
    }




}
