package com.mkhaleghy.cinema.main;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mkhaleghy.cinema.daylist.DayListFragment;
import com.mkhaleghy.cinema.R;
import com.mkhaleghy.cinema.ViewPagerTransform;
import com.mkhaleghy.cinema.tools.abstraction.OnPageChangeListener;

public class MainActivity extends AppCompatActivity implements DayListFragment.OnFragmentInteractionListener {
    public static final String TAG = "MainActivity";
    private ViewPager vp_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp_pager = findViewById(R.id.vp_pager);

        final MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), 30);
        vp_pager.setAdapter(pagerAdapter);
        vp_pager.setPageTransformer(true,new ViewPagerTransform());

        vp_pager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled() called with: position = [" + position + "], positionOffset = [" + positionOffset + "], positionOffsetPixels = [" + positionOffsetPixels + "] vp_pager.getCurrentItem()=" + vp_pager.getCurrentItem());
//                pagerAdapter.item(position).animate(positionOffset);
            }
        });





    }

}
