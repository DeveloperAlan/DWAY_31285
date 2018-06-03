package com.mad.dway.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mad.dway.R;

/**
 * Created by ang on 2/6/18.
 */

public class LobbyFragment extends Fragment {
    public LobbyFragment() {

    }

    public static LobbyFragment newInstance() {
        LobbyFragment fragment = new LobbyFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lobby, container, false);
        return view;
    }
}
