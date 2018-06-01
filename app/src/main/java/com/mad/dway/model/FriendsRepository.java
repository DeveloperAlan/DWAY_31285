package com.mad.dway.model;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mad.dway.presenter.MapPresenter;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by ang on 28/5/18.
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

    public static FriendsRepository getInstance() {
        Log.d("instance", "friends repo");
        if (sInstance == null) {
            sInstance = new FriendsRepository();

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mUsersRef = mFirebaseDatabase.getReference("users");
            mFirebaseAuth = FirebaseAuth.getInstance();
            mFirebaseUser = mFirebaseAuth.getCurrentUser();
            mFriendsList = new Friends();

            addFriendsLocationListener();

            if (mFirebaseUser == null) {
                mUserId = null;
            } else {
                mUserId = mFirebaseUser.getUid();
            }
        }
        return sInstance;
    }

    private static void addFriendsLocationListener() {
        mUsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static ArrayList<Friend> getFriendsList() {
        return mFriendsList.getFriendsList();
    }

    public static void updateFriendInFriendsList(Friend friend) {
        mFriendsList.updateFriendInFriendsList(friend);
    }
}
