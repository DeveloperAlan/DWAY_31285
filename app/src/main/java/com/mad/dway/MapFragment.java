package com.mad.dway;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.util.Date;

import im.delight.android.location.SimpleLocation;

/**
 * Created by ang on 7/5/18.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static final String TAG = MapFragment.class.getSimpleName();
    private MapView mMapView;
    private GoogleMap mGoogleMap;
    private LocationCallback mLocationCallback;
    private GoogleApiClient mGoogleApiClient;
    private SimpleLocation mLocation;

    public MapFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

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
        // construct a new instance of SimpleLocation
        mLocation = new SimpleLocation(getContext());

        // if we can't access the location yet
        if (!mLocation.hasLocationEnabled()) {
            // ask the user to enable location access
            SimpleLocation.openSettings(getContext());
        }

//        Log.d("location", String.valueOf(mLocation));

        mGoogleMap = googleMap;
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateMapLocation(mLocation);
        mLocation.setListener(new SimpleLocation.Listener() {
            @Override
            public void onPositionChanged() {
                updateMapLocation(mLocation);
            }
        });
    }

    private void updateMapLocation(SimpleLocation location) {
        Log.d("location 1", String.valueOf(location.getLatitude()));
        Log.d("location 2", String.valueOf(location.getLongitude()));

        LatLng lat = new LatLng(location.getLatitude(), location.getLongitude());
        mGoogleMap.addMarker(new MarkerOptions().position(lat)
                .title("Current location"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(lat));
    }
}
