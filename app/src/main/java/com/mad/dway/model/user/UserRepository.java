package com.mad.dway.model.user;

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
 * @author  12934713
 * @version 1.0
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

    /**
     * get instance of the user repository
     *
     * @return the instance
     */
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

    /**
     * is the user passed in the current user
     *
     * @param email
     * @return true or false
     */
    public boolean isCurrentUser(String email) {
        if (email == getEmail()) {
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

    /**
     * setup the local user from firebase into the app
     */
    private static void setUpLocalUser() {
        mLocalUser = new User(mFirebaseUser.getDisplayName(), mFirebaseUser.getEmail());
    }

    public static boolean isSignedIn() {
        return mSignedIn;
    }

    /**
     * setup the local user on the app if the user is signed in on firebase
     *
     */
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

    /**
     *  save the user into firebase
     */
    private static void saveUserIntoFirebase() {
        Log.d("uid", mUId);
        mUsersRef.child(mUId).setValue(mLocalUser);
    }

}
