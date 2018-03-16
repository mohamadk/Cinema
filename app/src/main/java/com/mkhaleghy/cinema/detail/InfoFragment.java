package com.mkhaleghy.cinema.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mkhaleghy.cinema.R;
import com.mkhaleghy.cinema.detail.models.Info;

public class InfoFragment extends Fragment {
    private static final String ARG_CONTENT = "c";

    private Info mContent;
    private TextView tv_title;
    private TextView tv_content;

    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance(Info content) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContent = getArguments().getParcelable(ARG_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        tv_title = view.findViewById(R.id.tv_title);
        tv_content = view.findViewById(R.id.tv_content);

        bindContent();

        return view;
    }

    private void bindContent() {
        tv_title.setText(mContent.title());
        tv_content.setText(mContent.content());
    }

}
