package com.mad.dway.view.fragments;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mad.dway.R;
import com.mad.dway.model.DirectionsEndPoint;
import com.mad.dway.model.Place;
import com.mad.dway.presenter.JourneySearchPresenter;
import com.mad.dway.view.fragments.dummy.DummyContent;

/**
 * Created by ang on 28/5/18.
 */

public class JourneyFragment extends Fragment implements JourneyDirectionsFragment.OnListFragmentInteractionListener {
    private ProgressDialog mProgressDialog;
    private DirectionsEndPoint mDirectionsEndPoint;

    private View mView;

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

        onInitialCreate();

        return mView;
    }

    private void onInitialCreate() {
        android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.add(R.id.fragment_journey_root, new JourneySearchFragment(), "fragment_journey_search");
        transaction.commit();
    }

    public View getView() {
        return mView;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
