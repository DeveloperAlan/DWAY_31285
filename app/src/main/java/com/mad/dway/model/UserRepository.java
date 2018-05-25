package com.mad.dway.model;

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
            mFirebaseUser = mFirebaseAuth.getInstance().getCurrentUser();
            if (mFirebaseUser == null ) {
                mSignedIn = false;
            } else {
                mSignedIn = true;
                setUpLocalUser();
            }
        }
        return sInstance;
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
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mUId = mFirebaseUser.getUid();

    }

}
