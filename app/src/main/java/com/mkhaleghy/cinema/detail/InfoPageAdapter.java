package com.mkhaleghy.cinema.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mkhaleghy.cinema.detail.models.Info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mk on 3/8/2018.
 */

public class InfoPageAdapter extends FragmentPagerAdapter {
    List<Info> infos;
    List<InfoFragment> fragments = new ArrayList<>();

    public InfoPageAdapter(FragmentManager fm, List<Info> infos) {
        super(fm);
        this.infos = infos;
    }

    public InfoPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position >= fragments.size()) {
            fragments.add(InfoFragment.newInstance(infos.get(position)));
        }
        return fragments.get(position);
    }


    @Override
    public int getCount() {
        return infos.size();
    }
}
