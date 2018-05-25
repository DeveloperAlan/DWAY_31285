package com.mad.dway.view.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mad.dway.R;
import com.mad.dway.model.User;
import com.mad.dway.presenter.AccountsPresenter;

/**
 * Created by ang on 14/5/18.
 */

public class AccountsFragment extends Fragment {
    public static final int RC_SIGN_IN = 123;
    private AccountsPresenter mAccountsPresenter;
    private Button mSignUpButton;
    private Button mLogOutButton;
    private LinearLayout mLoggedInLayout;
    private LinearLayout mNotLoggedInLayout;
    private View mView;

    public AccountsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccountsPresenter = new AccountsPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_accounts, container, false);
        mLoggedInLayout = mView.findViewById(R.id.logged_in);
        mNotLoggedInLayout= mView.findViewById(R.id.not_logged_in);
        getSignUpButton();
        getLogOutButton();
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
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                setLoggedInLayoutVisible();
                String uid = user.getUid();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference usersRef = database.getReference("users");
                User currentUser = new User("Alan", user.getEmail());
                usersRef.child(uid).setValue(currentUser);
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }

    public void getSignUpButton() {
        mSignUpButton = (Button) mView.findViewById(R.id.accounts_sign_up_btn);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAccountsPresenter.createFirebaseAccountsUI();
            }
        });
    }

    public void getLogOutButton() {
        mLogOutButton = mView.findViewById(R.id.accounts_log_out_btn);
        mLogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAccountsPresenter.logOutFirebaseAccounts();
            }
        });
    }

    public void setLoggedInLayoutVisible() {
        mLoggedInLayout.setVisibility(View.VISIBLE);
        mNotLoggedInLayout.setVisibility(View.GONE);
    }

    public void setNotLoggedInLayoutVisible() {
        mLoggedInLayout.setVisibility(View.GONE);
        mNotLoggedInLayout.setVisibility(View.VISIBLE);
    }
}
