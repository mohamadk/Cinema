package com.mkhaleghy.cinema.daylist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mkhaleghy.cinema.R;
import com.mkhaleghy.cinema.RecyclerItemAnimator;
import com.mkhaleghy.cinema.adapter.RecyclerAdapter;
import com.mkhaleghy.cinema.tools.RecyclerViewOverScrollDecorAdapterHandleAppbarLayout;

import java.util.Calendar;
import java.util.Date;

import me.everything.android.ui.overscroll.IOverScrollDecor;
import me.everything.android.ui.overscroll.IOverScrollUpdateListener;
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator;


public class DayListFragment extends BaseFragment implements RecyclerAdapter.OnAdapterInteractionListener{
    public static final String TAG = "DayListFragment";

    private static final String PARAM_DATE = "date";
    private Calendar date;

    private View mainView;
    private RecyclerView recyclerView;

    private RecyclerAdapter adapter;
    private RecyclerItemAnimator recyclerItemAnimator;
    private OnFragmentInteractionListener mListener;
    DayListViewModel viewModel;
    private int pos = -1;

    public DayListFragment() {
        // Required empty public constructor
    }

    public static DayListFragment newInstance(Date date, int pos) {
        DayListFragment fragment = new DayListFragment();
        Bundle args = new Bundle();
        args.putLong(PARAM_DATE, date.getTime());
        args.putInt("pos", pos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pos = getArguments().getInt("pos");
            date = Calendar.getInstance();
            date.setTime(new Date(getArguments().getLong(PARAM_DATE)));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_day_list, container, false);
        viewModel = ViewModelProviders.of(this).get(DayListViewModel.class);

        recyclerView = mainView.findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter = new RecyclerAdapter(getActivity(),this));

        RecyclerViewOverScrollDecorAdapterHandleAppbarLayout overScroll = new RecyclerViewOverScrollDecorAdapterHandleAppbarLayout(mListener.appbarLayout(), recyclerView);
        VerticalOverScrollBounceEffectDecorator decorator=new VerticalOverScrollBounceEffectDecorator(overScroll);
        decorator.setOverScrollUpdateListener((decor, state, offset) -> {
            if(!overScroll.isInAbsoluteEnd()){
                mListener.stretch(1+offset/mainView.getHeight(), (int) offset);
            }
        });

        recyclerItemAnimator = new RecyclerItemAnimator(
                 layoutManager
                , .5f
        );

        viewModel.items.observe(this,movieItemsObserver);

        Log.d(TAG, "onCreateView: this=" + this + " pos=" + pos);
        viewModel.start();

        return mainView;
    }


    Observer<DayList> movieItemsObserver=new Observer<DayList>() {
        @Override
        public void onChanged(@Nullable DayList dayList) {
            Log.d(TAG, "onChanged() called with: elements.size = [" + dayList.movies().size() + "]");
            adapter.bindItems(dayList.movies());
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void animate(float fraction) {
        Log.d(TAG, "animate() called with: fraction = [" + fraction + "] recyclerItemAnimator=" + recyclerItemAnimator + " date=" + date + " posi=" + pos + " this=" + this);
        if (recyclerItemAnimator != null) {
            recyclerItemAnimator.animate(fraction);
        }
    }

    @Override
    public void detailSelected() {
        mListener.detailSelected();
    }


    public interface OnFragmentInteractionListener {
        AppBarLayout appbarLayout();

        void stretch(float offset,int offsetInPx);

        void detailSelected();
    }


}
