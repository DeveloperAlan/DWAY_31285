package com.mad.dway.view.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mad.dway.BuildConfig;
import com.mad.dway.R;
import com.mad.dway.model.DirectionsEndPoint;
import com.mad.dway.model.Place;
import com.mad.dway.model.PlacesEndPoint;
import com.mad.dway.presenter.JourneyPresenter;
import com.mad.dway.view.fragments.dummy.DummyContent;

/**
 * Created by ang on 28/5/18.
 */

public class JourneyFragment extends Fragment implements SearchResultsFragment.OnListFragmentInteractionListener {

    private JourneyPresenter mJourneyPresenter;
    private ProgressDialog mProgressDialog;
    private DirectionsEndPoint mDirectionsEndPoint;
    private EditText mSearchEditText;
    private View mView;
    private Button mSearchButton;

    public JourneyFragment() {

    }

    public static JourneyFragment newInstance() {
        JourneyFragment fragment = new JourneyFragment();
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
        mView = inflater.inflate(R.layout.fragment_journey, container, false);
        mJourneyPresenter = new JourneyPresenter(this);
        mSearchEditText = mView.findViewById(R.id.fragment_journey_search_edit_text);
        mSearchButton = mView.findViewById(R.id.destination_search_button);
//        addSearchButtonListener();
        return mView;
    }

    public View getView() {
        return mView;
    }

    @Override
    public void onListFragmentInteraction(Place place) {

    }
}
