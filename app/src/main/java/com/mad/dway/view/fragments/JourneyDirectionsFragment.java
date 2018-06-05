package com.mad.dway.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mad.dway.R;
import com.mad.dway.model.location.CurrentLocationRepository;
import com.mad.dway.model.directions.Directions;
import com.mad.dway.model.directions.DirectionsRepository;
import com.mad.dway.model.places.Place;

/**
 * Journey Directions Fragment for the Journey directions functionality and view. The fragments
 * contains the directions list, which is scrollable to get the directions for the user
 *
 * @author  12934713
 * @version 1.0
 */
public class JourneyDirectionsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private static Place mDestination;
    private static CurrentLocationRepository mCurrLocation;
    private static DirectionsRepository mDirectionsRepo;
    private static Directions mDirections;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public JourneyDirectionsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static JourneyDirectionsFragment newInstance(Place destination, CurrentLocationRepository currLocation) {
        mDestination = destination;
        mCurrLocation = currLocation;
        mDirectionsRepo = DirectionsRepository.getInstance();

        JourneyDirectionsFragment fragment = new JourneyDirectionsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journey_directions_list, container, false);

        mDirections = null;
        mDirectionsRepo.getDirectionsFromAtoB(mCurrLocation, mDestination);
        mDirections = mDirectionsRepo.getDirections();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyJourneyDirectionsRecyclerViewAdapter(mDirections.getDirectionInstructions(), mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(String direction);
    }
}
