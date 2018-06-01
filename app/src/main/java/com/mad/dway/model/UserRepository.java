package com.mad.dway.model;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * UserRepository for the User instance. Monitors for both the local and remote (Firebase)
 * data
 *
 * Created by 12934713 on 25/5/18.
 */

public class UserRepository {
    private static UserRepository sInstance = null;
    private static FirebaseDatabase mFirebaseDatabase;
    private static DatabaseReference mUsersRef;
    private static FirebaseAuth mFirebaseAuth;
    private static FirebaseUser mFirebaseUser;
    private static boolean mSignedIn;
    private static User mLocalUser;
    private static String mUId;

    private UserRepository() {

    }

    public static UserRepository getInstance() {
        if (sInstance == null) {
            sInstance = new UserRepository();

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mUsersRef = mFirebaseDatabase.getReference("users");
            mFirebaseAuth = FirebaseAuth.getInstance();
            mFirebaseUser = mFirebaseAuth.getCurrentUser();

            if (mFirebaseUser == null ) {
                mSignedIn = false;
            } else {
                mSignedIn = true;
                setUpLocalUser();
            }
        }
        return sInstance;
    }

    public boolean isCurrentUser(String name) {
        if (name == getName()) {
            return true;
        }
        return false;
    }

    public String getName() {
        return mLocalUser.getName();
    }

    public String getEmail() {
        return mLocalUser.getEmail();
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    private static void setUpLocalUser() {
        mLocalUser = new User(mFirebaseUser.getDisplayName(), mFirebaseUser.getEmail());
    }
    
    private static void saveLocalUserIntoFirebase() {
        mUsersRef.child(mUId).setValue(mLocalUser);
    }

    public static void onSignUp() {

    }

    public static boolean isSignedIn() {
        return mSignedIn;
    }

    public static void onSignIn() {
        mSignedIn = true;
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mUId = mFirebaseUser.getUid();
        setUpLocalUser();
    }

    public static LatLng getLatLng() {
        return mLocalUser.getLatLng();
    }

    public static void setLatLng(LatLng latLng) {
        mLocalUser.setLatLng(latLng);
        if (mUId != null) {
            saveUserIntoFirebase();
        }
    }

    private static void saveUserIntoFirebase() {
        Log.d("uid", mUId);
        mUsersRef.child(mUId).setValue(mLocalUser);
    }

}
