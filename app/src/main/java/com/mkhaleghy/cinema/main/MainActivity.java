package com.mkhaleghy.cinema.main;

import android.app.SharedElementCallback;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.mkhaleghy.cinema.R;
import com.mkhaleghy.cinema.ViewPagerTransform;
import com.mkhaleghy.cinema.daylist.DayListFragment;
import com.mkhaleghy.cinema.tools.RampImageView;
import com.mkhaleghy.cinema.tools.SlidingTabLayout;
import com.mkhaleghy.cinema.tools.abstraction.OnPageChangeListener;
import com.mkhaleghy.cinema.tools.abstraction.Utils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DayListFragment.OnFragmentInteractionListener {
    public static final String TAG = "MainActivity";
    private ViewPager vp_pager;
    private SlidingTabLayout stl_tabs;
    private Toolbar toolbar;
    private MaterialMenuDrawable materialMenu;
    private AppBarLayout apl_appbar;
    private RampImageView riv_ramp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_main);

        initViews();


        stl_tabs.setSelectedIndicatorColors(Color.TRANSPARENT);
        stl_tabs.setSelectedScaleFactor(.8f);

        final MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), 30);
        vp_pager.setAdapter(pagerAdapter);
        vp_pager.setPageTransformer(true, new ViewPagerTransform());

        stl_tabs.setViewPager(vp_pager);

        vp_pager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled() called with: position = [" + position + "], positionOffset = [" + positionOffset + "], positionOffsetPixels = [" + positionOffsetPixels + "] vp_pager.getCurrentItem()=" + vp_pager.getCurrentItem());
            }
        });


        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (materialMenu.getIconState() == MaterialMenuDrawable.IconState.ARROW) {
                    materialMenu.animateIconState(MaterialMenuDrawable.IconState.BURGER);
                } else {
                    materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW);
                }
            }
        });
        materialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.REGULAR);
        toolbar.setNavigationIcon(materialMenu);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        vp_pager = findViewById(R.id.vp_pager);
        stl_tabs = findViewById(R.id.stl_tabs);
        apl_appbar=findViewById(R.id.apl_appbar);
        riv_ramp=findViewById(R.id.riv_ramp);
        apl_appbar.setPadding(apl_appbar.getPaddingLeft(), Utils.getStatusBarHeight(), apl_appbar.getPaddingRight(), apl_appbar.getPaddingBottom());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setExitSharedElementCallback(new SharedElementCallback() {
                @Override
                public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements , List<View> sharedElementSnapshots) {
                    super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
                    Log.d(TAG, "setExitSharedElementCallback: ");
                }
            });
        }

    }

    @Override
    public AppBarLayout appbarLayout() {
        return apl_appbar;
    }

    @Override
    public void stretch(float offset,int offsetInPx) {
        Log.d(TAG, "stretch() called with: offset = [" + offset + "], offsetInPx = [" + offsetInPx + "] trans="+((offset-1)*(apl_appbar.getHeight()/2)));
        apl_appbar.setTranslationY((offset-1)*(apl_appbar.getHeight()/2));
        apl_appbar.setScaleY(offset);

        riv_ramp.setRampDy(-offsetInPx);// -offsetInPx "-" because riv_ramp rotated 180
    }
}
