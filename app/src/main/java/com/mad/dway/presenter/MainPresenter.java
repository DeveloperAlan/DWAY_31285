package com.mad.dway.presenter;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.mad.dway.model.FriendsRepository;
import com.mad.dway.model.UserRepository;
import com.mad.dway.view.activities.MainActivity;

/**
 * Created by ang on 21/5/18.
 */

public class MainPresenter {

    private MainActivity mMainView;
    private UserRepository mUserRepository;
    private FriendsRepository mCurrentFriends;

    public MainPresenter(MainActivity view) {
        mUserRepository = UserRepository.getInstance();
        mCurrentFriends = FriendsRepository.getInstance();
        mMainView = view;
    }

    public void updateUIDependingOnCurrentUser(FirebaseUser currentUser) {
        if (currentUser.isAnonymous() == false) {

        }
    }

    public void checkUserInstance() {
        if (mUserRepository.isSignedIn()) {
            Log.d("user log in", "Logged in");
        } else {
            Log.d("user log in", "Not Logged in");
        }
    }
}
