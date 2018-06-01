package com.mad.dway.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by ang on 27/5/18.
 */

public class Friends extends Observable implements Serializable{
    private ArrayList<Friend> mFriendsList;

    public Friends() {
        mFriendsList = new ArrayList<Friend>();
    }

    public ArrayList<Friend> getFriendsList() {
        return mFriendsList;
    }

    public void setFriendsList(ArrayList<Friend> friendsList) {
        mFriendsList = friendsList;
    }

    public void updateFriendInFriendsList(Friend friend) {
        for (int i = 0; i < mFriendsList.size(); i++) {
            if (mFriendsList.get(i).getUId() == friend.getUId()) {
                mFriendsList.get(i).setLatLng(friend.getLatLng());
                return;
            }
        }
        mFriendsList.add(friend);

    }
}
