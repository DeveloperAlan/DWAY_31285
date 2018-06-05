package com.mad.dway.view.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.auth.IdpResponse;
import com.mad.dway.R;
import com.mad.dway.model.user.UserRepository;
import com.mad.dway.presenter.AccountsPresenter;

/**
 * Accounts Fragment for the Accounts functionality and view. The fragments contains the accounts
 * sign in, resgister and log out code as well as the UI code for accounts
 *
 * @author  12934713
 * @version 1.0
 */

public class AccountsFragment extends Fragment {

    // callback code for firebase ui
    public static final int RC_SIGN_IN = 123;
    private AccountsPresenter mAccountsPresenter;
    private View mView;
    private UserRepository mCurrentUser;

    public AccountsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentUser = UserRepository.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_accounts, container, false);
        mAccountsPresenter = new AccountsPresenter(this);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                mAccountsPresenter.setLoggedInLayoutVisible();
                mCurrentUser.onSignIn();
                mAccountsPresenter.setUserNameEmailText();
            } else {
                Toast.makeText(getContext(), R.string.sign_in_error_text, Toast.LENGTH_SHORT);
            }
        }
    }

    /**
     * Returns the layout
     *
     * @return View     The layout the fragment class is associated with
     */
    public View getView() {
        return mView;
    }
}
