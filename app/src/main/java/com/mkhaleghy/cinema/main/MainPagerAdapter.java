package com.mkhaleghy.cinema.main;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.mkhaleghy.cinema.R;
import com.mkhaleghy.cinema.app.Ci;
import com.mkhaleghy.cinema.daylist.DayListFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mk on 3/2/2018.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    SimpleDateFormat sdf=new SimpleDateFormat("MMM dd");
    private int count=30;

    private SparseArray<DayListFragment> items=new SparseArray<>();

    public MainPagerAdapter(FragmentManager fm,int count) {
        super(fm);
        this.count=count;
    }

    @Override
    public Fragment getItem(int position) {
        DayListFragment fragment;
        if(position>=items.size()){
            fragment = DayListFragment.newInstance(getItemTime(position),position);
            items.append(position,fragment);
        }else{
            fragment=items.get(position);
        }

        return fragment;
    }

    private Date getItemTime(int position) {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,position);

        return calendar.getTime();
    }

    public DayListFragment item(int position){
        return items.get(position);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return getTitle(position);
    }

    private CharSequence getTitle(int position) {
        if(position==0){
            return Ci.context().getString(R.string.today);
        }
        return sdf.format(getItemTime(position));
    }
}
