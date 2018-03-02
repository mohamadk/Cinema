package com.mkhaleghy.cinema;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.mkhaleghy.cinema.adapter.Element;
import com.mkhaleghy.cinema.adapter.DayListContract;
import com.mkhaleghy.cinema.adapter.RecyclerAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DayListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DayListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DayListFragment extends BaseFragment implements DayListContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View mainView;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private RecyclerItemAnimator recyclerItemAnimator;
    private OnFragmentInteractionListener mListener;
    DayListContract.Presenter presenter;

    public DayListFragment() {
        // Required empty public constructor
    }

    public static DayListFragment newInstance() {
        DayListFragment fragment = new DayListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_day_list, container, false);
        presenter=new DayListPresenter(this);

        recyclerView = mainView.findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter = new RecyclerAdapter(getActivity()));

        recyclerItemAnimator = new RecyclerItemAnimator(
                recyclerView
                ,layoutManager
                ,.5f
        );

        SeekBar seekBar=mainView.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                recyclerItemAnimator.animate(progress/100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        presenter.start();

        return mainView;
    }

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

    @Override
    public void bind(ArrayList<Element> movies) {
        adapter.bindItems(movies);
    }

    public void animate(float fraction){
        recyclerItemAnimator.animate(fraction);
    }


    public interface OnFragmentInteractionListener {

    }
}
