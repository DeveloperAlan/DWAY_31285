package com.mad.dway.presenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mad.dway.model.Friend;
import com.mad.dway.model.Friends;
import com.mad.dway.model.FriendsRepository;
import com.mad.dway.model.UserRepository;
import com.mad.dway.view.fragments.MapFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * Presenter for the Maps Fragment
 *
 * Created by ang on 21/5/18.
 */

public class MapPresenter implements OnMapReadyCallback, Observer {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static final float[] MARKER_COLOURS = {
            BitmapDescriptorFactory.HUE_AZURE,
            BitmapDescriptorFactory.HUE_BLUE,
            BitmapDescriptorFactory.HUE_CYAN,
            BitmapDescriptorFactory.HUE_GREEN,
            BitmapDescriptorFactory.HUE_MAGENTA,
            BitmapDescriptorFactory.HUE_ORANGE,
            BitmapDescriptorFactory.HUE_ROSE,
            BitmapDescriptorFactory.HUE_VIOLET,
            BitmapDescriptorFactory.HUE_YELLOW,
    };

    private MapFragment mMapView;
    private LocationRequest mLocationRequest;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;
    private GoogleMap mGoogleMap;
    private UserRepository mCurrentUser;
    private FriendsRepository mCurrentFriends;
    private DatabaseReference mUsersRef;
    private boolean mHasCameraMoved = false;

    public MapPresenter(MapFragment view) {
        mMapView = view;
    }

    public void onMapsFragmentCreate() {
        mCurrentUser = UserRepository.getInstance();
        mCurrentFriends = FriendsRepository.getInstance();
        mUsersRef = FirebaseDatabase.getInstance().getReference("users");
    }

    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mMapView.getActivity());
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (mCurrentUser.isSignedIn()) {
                    setAllFriendsLocationsOnMap();
                }
                List<Location> locationList = locationResult.getLocations();
                if (locationList.size() > 0) {
                    //The last location in the list is the newest
                    Location location = locationList.get(locationList.size() - 1);
                    Log.i("MapsActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());
                    mLastLocation = location;
                    if (mCurrLocationMarker != null) {
                        mCurrLocationMarker.remove();
                    }

                    //Place current location marker
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions();

                    Log.d("is user null", String.valueOf(mCurrentUser.isSignedIn()));
                    if (mCurrentUser.isSignedIn()) {
                        mCurrentUser.setLatLng(latLng);
                    }
                    markerOptions.position(latLng);


                    markerOptions.title("That's you!");
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

                    //move map camera
                    if (!mHasCameraMoved) {
                        mHasCameraMoved = true;
                        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    }
                }
            }
        };
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000); // two minute interval
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(mMapView.getContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted)
                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                mGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        }
        else {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
            mGoogleMap.setMyLocationEnabled(true);
        }
    }

    private void setAllFriendsLocationsOnMap() {
        mUsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String name = childSnapshot.child("name").getValue().toString();
                    if (mCurrentUser.isCurrentUser(name)) continue;
                    String uId = childSnapshot.getKey();

                    LatLng latLng = new LatLng(
                            (Double) childSnapshot.child("latLng").child("latitude").getValue(),
                            (Double) childSnapshot.child("latLng").child("longitude").getValue()
                    );
                    Log.d("latlng", latLng.toString());
                    Friend friend = new Friend(uId, name, latLng);
                    mCurrentFriends.updateFriendInFriendsList(friend);
                    updateFriendLocationOnMap(friend);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void updateFriendLocationOnMap(Friend friend) {
        if (friend.getLatLng() == null ) return;
        Log.d("frineds gps", friend.getLatLng().toString());
        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(friend.getLatLng());

        markerOptions.title("That's " + friend.getName());
        int rnd = new Random().nextInt(MARKER_COLOURS.length);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(MARKER_COLOURS[rnd]));
        mGoogleMap.addMarker(markerOptions);
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(mMapView.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(mMapView.getActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(mMapView.getContext())
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(mMapView.getActivity(),
                                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(mMapView.getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(mMapView.getContext(),
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
//                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
