package com.mad.dway.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by ang on 25/5/18.
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
