package com.mad.dway.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by ang on 27/5/18.
 */

public class Friend implements Serializable {
    private String mUId;
    private String mName;
    private LatLng mLatLng;

    public Friend(String uId, String name, LatLng latLng) {
        mUId = uId;
        mName = name;
        mLatLng = latLng;
    }

    public String getUId() {
        return mUId;
    }

    public void setUId(String UId) {
        mUId = UId;
    }

    public LatLng getLatLng() {
        return mLatLng;
    }

    public void setLatLng(LatLng latLng) {
        mLatLng = latLng;
    }

    public String getName() {

        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
