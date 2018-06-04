package com.mad.dway.model.location;

/**
 * Current Location Repository which processes the user location and saves the location into Firebase
 *
 * @author  12934713
 * @version 1.0
 */

public class CurrentLocationRepository {
    private static CurrentLocationRepository mLocation;
    private static double mLatitude;
    private static double mLongitude;

    private CurrentLocationRepository() {

    }

    public static CurrentLocationRepository getInstance() {
        if (mLocation == null) {
            mLocation = new CurrentLocationRepository();
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

    public static CurrentLocationRepository getLocation() {
        return mLocation;
    }

    public static void setLocation(CurrentLocationRepository location) {
        mLocation = location;
    }
}
