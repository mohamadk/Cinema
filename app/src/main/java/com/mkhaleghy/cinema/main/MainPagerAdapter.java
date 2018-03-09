package com.mkhaleghy.cinema.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.mkhaleghy.cinema.daylist.DayListFragment;

import java.util.Calendar;

/**
 * Created by mk on 3/2/2018.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
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
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.DATE,position);
            fragment = DayListFragment.newInstance(calendar.getTime(),position);
            items.append(position,fragment);
        }else{
            fragment=items.get(position);
        }

        return fragment;
    }

    public DayListFragment item(int position){
        return items.get(position);
    }

    @Override
    public int getCount() {
        return count;
    }





}
