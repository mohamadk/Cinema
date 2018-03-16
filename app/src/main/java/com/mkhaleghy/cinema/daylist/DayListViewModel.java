package com.mkhaleghy.cinema.daylist;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.mkhaleghy.cinema.DataFetcher;
import com.mkhaleghy.cinema.FakeRepository;
import com.mkhaleghy.cinema.adapter.Element;

import java.util.List;

/**
 * Created by mk on 3/1/2018.
 */

public class DayListViewModel extends ViewModel {
    List<Element> elements;

    MutableLiveData<DayList> items = new MutableLiveData<>();
    DataFetcher dataFetcher;


    public void start() {
        if (dataFetcher == null) {
            dataFetcher = new FakeRepository();
        }

        if (elements == null) {
            dataFetcher.getData(items);
        }
    }
}
