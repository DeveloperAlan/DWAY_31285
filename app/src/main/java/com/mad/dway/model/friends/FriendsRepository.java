package com.mad.dway.model.friends;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mad.dway.R;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Friends Repository which contains all the methods per instance to get friends list of the user
 * and methods to modify and get friends
 *
 * @author  12934713
 * @version 1.0
 */

public class FriendsRepository extends Observable {
    private static FriendsRepository sInstance = null;
    private static FirebaseDatabase mFirebaseDatabase;
    private static DatabaseReference mUsersRef;
    private static FirebaseAuth mFirebaseAuth;
    private static FirebaseUser mFirebaseUser;
    private static String mUserId;
    private static Friends mFriendsList;

    private FriendsRepository() {

    }

    /**
     * get instance of the friends repository
     *
     * @return the instance of friends repository
     */
    public static FriendsRepository getInstance() {
        Log.d("Friends Repository", String.valueOf(R.string.start_friends_repo));
        if (sInstance == null) {
            sInstance = new FriendsRepository();

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mUsersRef = mFirebaseDatabase.getReference("users");
            mFirebaseAuth = FirebaseAuth.getInstance();
            mFirebaseUser = mFirebaseAuth.getCurrentUser();
            mFriendsList = new Friends();

            if (mFirebaseUser == null) {
                mUserId = null;
            } else {
                mUserId = mFirebaseUser.getUid();
            }
        }
        return sInstance;
    }

    /**
     * get the friends list of the user
     *
     * @return the friends list of the user
     */
    public static ArrayList<Friend> getFriendsList() {
        return mFriendsList.getFriendsList();
    }

    /**
     * Update the friends location in the friends list
     *
     * @param friend
     * @return null
     */
    public static void updateFriendInFriendsList(Friend friend) {
        mFriendsList.updateFriendInFriendsList(friend);
    }
}
