package com.mkhaleghy.cinema;

import android.arch.lifecycle.MutableLiveData;

import com.mkhaleghy.cinema.adapter.Element;
import com.mkhaleghy.cinema.daylist.DayList;

import java.util.List;

/**
 * Created by mk on 3/8/2018.
 */

public interface DataFetcher {

    void getData(MutableLiveData<DayList> elementsData);


}
