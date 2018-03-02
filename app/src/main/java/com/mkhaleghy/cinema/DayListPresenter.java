package com.mkhaleghy.cinema;





import com.mkhaleghy.cinema.adapter.Element;
import com.mkhaleghy.cinema.adapter.DayListContract;

import java.util.ArrayList;

/**
 * Created by mk on 3/1/2018.
 */

public class DayListPresenter implements DayListContract.Presenter{

    DayListContract.View view;

    public DayListPresenter(DayListContract.View view) {
        this.view = view;
    }

    public void start(){
        ArrayList<Element> items=fetchItems();
        view.bind(items);
    }

    private ArrayList<Element> fetchItems() {
        ArrayList<Element> items=new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            items.add(new Movie(R.drawable.kong,"Kong Skull Island","by jordan Vogt-Roberts",5,"Drama | Adventures |","7:10 PM"));
        }
        return items;
    }


}
