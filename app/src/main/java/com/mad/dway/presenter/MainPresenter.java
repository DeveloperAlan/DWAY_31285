package com.mad.dway.presenter;

import android.support.v4.view.ViewPager;
import android.util.Log;

import com.mad.dway.R;
import com.mad.dway.model.friends.FriendsRepository;
import com.mad.dway.model.user.UserRepository;
import com.mad.dway.view.activities.MainActivity;

/**
 * Main Presenter for the activity. Checks user instance for the app
 *
 * @author  12934713
 * @version 1.0
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

    /**
     * check the user instance of the app and check if the user is logged in
     *
     * @return  null
     */
    public void checkUserInstance() {
        if (mUserRepository.isSignedIn()) {
            Log.d(mMainView.getString(R.string.user_repo_signed_in), mMainView.getString(R.string.logged_in));
        } else {
            Log.d(mMainView.getString(R.string.user_repo_signed_in), mMainView.getString(R.string.not_logged_in));
        }
    }

}
