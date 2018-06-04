package com.mad.dway.model.user;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * User model for the user, extends the User model for Firebase for extra details about the user,
 * such as latitude and longitude
 *
 * @author  12934713
 * @version 1.0
 */
public class User implements Serializable{
    private String mName;
    private String mEmail;
    private LatLng mLatLng;

    public User () {
        this.mName = "";
        this.mEmail = "";
        this.mLatLng = null;
    }

    public User (String name, String email) {
        this.mName = name;
        this.mEmail = email;
    }

    public String getName() {
        return mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public LatLng getLatLng() {
        return mLatLng;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public void setLatLng(LatLng latLng) {
        mLatLng = latLng;
    }
}
