package com.mkhaleghy.cinema;

import android.arch.lifecycle.MutableLiveData;

import com.mkhaleghy.cinema.app.Ci;
import com.mkhaleghy.cinema.daylist.DayList;
import com.mkhaleghy.cinema.tools.abstraction.Utils;

/**
 * Created by mk on 3/8/2018.
 */

public class FakeRepository implements DataFetcher {
    @Override
    public void getData(MutableLiveData<DayList> elementsData) {

        DayList dayList = Ci.gson.fromJson(Utils.loadFileFromAssets("daylist.json"), DayList.class);
        elementsData.postValue(dayList);
    }
}
