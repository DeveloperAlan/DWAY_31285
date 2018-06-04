package com.mad.dway.model.friends;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Friend model which contains information needs to display to user, such as name and location
 *
 * @author  12934713
 * @version 1.0
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
