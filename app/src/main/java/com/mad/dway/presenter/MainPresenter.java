package com.mad.dway.presenter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.mad.dway.R;
import com.mad.dway.model.FriendsRepository;
import com.mad.dway.model.UserRepository;
import com.mad.dway.view.activities.MainActivity;
import com.mad.dway.view.fragments.SearchResultsFragment;

/**
 * Created by ang on 21/5/18.
 */

public class MainPresenter {

    private MainActivity mMainView;
    private UserRepository mUserRepository;
    private FriendsRepository mCurrentFriends;
    private ViewPager mViewPager;

    public MainPresenter(MainActivity view) {
        mUserRepository = UserRepository.getInstance();
        mCurrentFriends = FriendsRepository.getInstance();
        mMainView = view;
        mViewPager = mMainView.getViewPager();
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

    public Fragment getFragmentByPosition(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = (Fragment) mViewPager.getTag(R.integer.page1);
                break;

            case 1:

                fragment = (Fragment) mViewPager.getTag(R.integer.page2);
                break;

            case 2:
                fragment = (Fragment) mViewPager.getTag(R.integer.page3);
                break;
        }

        return fragment;
    }
}
