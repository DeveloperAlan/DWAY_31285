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
 * The journey search fragment which contains the search bar and button which can then search
 * locations for the user
 *
 * @author  12934713
 * @version 1.0
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

    /**
     * Create a new instance of the journey fragment for the main activity on create
     *
     * @return View  the journey search view
     */
    public View getView() {
        return mView;
    }
}
