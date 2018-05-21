package com.mad.dway.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mad.dway.R;
import com.mad.dway.presenter.ExplorePresenter;

/**
 * Created by ang on 14/5/18.
 */

public class ExploreFragment extends Fragment {

    private ExplorePresenter mExplorePresenter;

    public ExploreFragment() {

    }

    public static ExploreFragment newInstance() {
        ExploreFragment fragment = new ExploreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mExplorePresenter = new ExplorePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        return view;

    }

}
