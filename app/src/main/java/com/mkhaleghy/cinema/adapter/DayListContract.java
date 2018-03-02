package com.mkhaleghy.cinema.adapter;

import java.util.ArrayList;

/**
 * Created by mk on 3/1/2018.
 */

public interface DayListContract {

    interface View {

        void bind(ArrayList<Element> movies);
    }

    interface Presenter {

        void start();
    }

}
