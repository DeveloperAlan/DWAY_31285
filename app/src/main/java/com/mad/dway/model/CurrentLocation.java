package com.mad.dway.model;

/**
 * Created by ang on 3/6/18.
 */

public class CurrentLocation {
    private static CurrentLocation mLocation;
    private static double mLatitude;
    private static double mLongitude;

    private CurrentLocation() {

    }

    public static CurrentLocation getInstance() {
        if (mLocation == null) {
            mLocation = new CurrentLocation();
        }
        return mLocation;
    }

    public static double getLatitude() {
        return mLatitude;
    }

    public static void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public static double getLongitude() {
        return mLongitude;
    }

    public static void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public static CurrentLocation getLocation() {
        return mLocation;
    }

    public static void setLocation(CurrentLocation mLocation) {
        CurrentLocation.mLocation = mLocation;
    }
}
