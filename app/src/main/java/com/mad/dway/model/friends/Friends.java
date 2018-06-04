package com.mad.dway.model.friends;

import com.mad.dway.model.friends.Friend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * List of Friends of the user which can be returned to populate the map with other user's locations
 *
 * @author  12934713
 * @version 1.0
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
