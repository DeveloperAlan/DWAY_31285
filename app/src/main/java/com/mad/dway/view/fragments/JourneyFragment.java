package com.mad.dway.view.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mad.dway.R;

/**
 * Root Journey fragment which contains the base journey fragment tab in the main activity. New
 * fragments are saved over this fragment in order to get fragment manager working in journy tab
 *
 * @author  12934713
 * @version 1.0
 */

public class JourneyFragment extends Fragment {
    private View mView;

    public JourneyFragment() {

    }

    /**
     * Create a new instance of the journey fragment for the main activity on create
     *
     * @return JourneyFramgent  the journey instance
     */
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

    /**
     * Add the fragment of the journey search to this root fragment
     *
     * @return null
     */
    private void onInitialCreate() {
        android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.add(R.id.fragment_journey_root, new JourneySearchFragment(), "fragment_journey_search");
        transaction.commit();
    }

    /**
     * Create a new instance of the journey fragment for the main activity on create
     *
     * @return View  the journey view
     */
    public View getView() {
        return mView;
    }

}
