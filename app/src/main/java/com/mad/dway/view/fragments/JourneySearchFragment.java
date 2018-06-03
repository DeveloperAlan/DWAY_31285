package com.mad.dway.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mad.dway.R;
import com.mad.dway.presenter.JourneySearchPresenter;

/**
 * Created by ang on 3/6/18.
 */

public class JourneySearchFragment extends Fragment {
    private JourneySearchPresenter mJourneySearchPresenter;
    private EditText mSearchEditText;
    private Button mSearchButton;
    private View mView;

    public static JourneySearchFragment newInstance() {
        JourneySearchFragment fragment = new JourneySearchFragment();
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
        mView = inflater.inflate(R.layout.fragment_journey_search, container, false);
        mJourneySearchPresenter = new JourneySearchPresenter(this);
        mSearchEditText = mView.findViewById(R.id.fragment_journey_search_edit_text);
        mSearchButton = mView.findViewById(R.id.destination_search_button);

        return mView;
    }

    public View getView() {
        return mView;
    }
}
