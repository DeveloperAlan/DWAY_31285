package com.mad.dway.view.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.mad.dway.R;
import com.mad.dway.presenter.MapPresenter;

/**
 * Map fragment which contains the map presenter to show the Google map implementation
 *
 * @author  12934713
 * @version 1.0
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    public static final String TAG = MapFragment.class.getSimpleName();
    private MapPresenter mMapPresenter;

    public MapFragment() {
    }

    /**
     * Returns a new instance of this fragment
     *
     * @return  MapFragment the google map itself
     */
    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mMapPresenter = new MapPresenter(this);
        this.mMapPresenter.onMapsFragmentCreate();
    }

    /**
     * Create the view as well as adding the map fragment to the view from the Google Maps SDK
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return  view    the maps view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        SupportMapFragment fragment = new SupportMapFragment();
        transaction.add(R.id.map, fragment);
        transaction.commit();

        fragment.getMapAsync(this);


        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMapPresenter.onMapReady(googleMap);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        mMapPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
