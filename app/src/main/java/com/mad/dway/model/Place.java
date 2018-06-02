package com.mad.dway.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.model.OpeningHours;
import com.google.maps.model.Photo;

/**
 * Created by ang on 1/6/18.
 */

public class Place {
    private String mName;
    private double mLatitude;
    private double mLongitude;
    private String mFormattedAddress;
    private OpeningHours mOpeningHours;
    private float mRating;
    private Photo[] mPhoto;

    public Place() {

    }

    public Place(String name, double latitude, double longitude, String formattedAddress, Photo[] photo) {
        mName = name;
        mLatitude = latitude;
        mLongitude = longitude;
        mFormattedAddress = formattedAddress;
        mPhoto = photo;
    }

    public Place(String name, double latitude, double longitude, String formattedAddress, OpeningHours openingHours, float rating, Photo[] photo) {
        mName = name;
        mLatitude = latitude;
        mLongitude = longitude;
        mFormattedAddress = formattedAddress;
        mOpeningHours = openingHours;
        mRating = rating;
        mPhoto = photo;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public String getFormattedAddress() {
        return mFormattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        mFormattedAddress = formattedAddress;
    }

    public OpeningHours getOpeningHours() {
        return mOpeningHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        mOpeningHours = openingHours;
    }

    public float getRating() {
        return mRating;
    }

    public void setRating(float rating) {
        mRating = rating;
    }

    public Photo[] getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Photo[] photo) {
        mPhoto = photo;
    }
}
