package com.mad.dway.presenter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mad.dway.R;
import com.mad.dway.model.user.UserRepository;
import com.mad.dway.view.fragments.AccountsFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Accounts Presenter for the fragment. Retreives the firebase accounts ui as well as sort out logout
 * functionality.
 *
 * @author  12934713
 * @version 1.0
 */

public class AccountsPresenter {

    private AccountsFragment mAccountsFragment;
    private View mView;
    private List<AuthUI.IdpConfig> providers;
    private Button mSignUpButton;
    private Button mLogOutButton;
    private TextView mNameTextView;
    private TextView mEmailTextView;
    private LinearLayout mLoggedInLayout;
    private LinearLayout mNotLoggedInLayout;
    private UserRepository mCurrentUser;

    public AccountsPresenter(AccountsFragment view) {
        mAccountsFragment = view;
        mCurrentUser = UserRepository.getInstance();
        mView = mAccountsFragment.getView();

        providers = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build());

        // set view by id to objects
        mLoggedInLayout = mView.findViewById(R.id.logged_in);
        mNotLoggedInLayout= mView.findViewById(R.id.not_logged_in);
        mNameTextView = mView.findViewById(R.id.name_text_view);
        mEmailTextView = mView.findViewById(R.id.email_text_view);

        // get the buttons and add click listeners
        getSignUpButton();
        getLogOutButton();
    }

    /**
     * Create the firebase ui for accounts
     *
     * @return null
     */
    public void createFirebaseAccountsUI() {
        mAccountsFragment.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                mAccountsFragment.RC_SIGN_IN);
    }

    /**
     * Log out the firebase account and set the app to not be logged in
     *
     * @return null
     */
    public void logOutFirebaseAccounts() {
        AuthUI.getInstance()
                .signOut(mAccountsFragment.getContext())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        setNotLoggedInLayoutVisible();
                    }
                });
    }

    /**
     * Get the Log out button and set the onClickListener
     *
     * @return null
     */
    public void getLogOutButton() {
        mLogOutButton = mView.findViewById(R.id.accounts_log_out_btn);
        mLogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOutFirebaseAccounts();
            }
        });
    }

    /**
     * Get the sign up button and set the onClickListener
     *
     * @return null
     */
    public void getSignUpButton() {
        mSignUpButton = (Button) mView.findViewById(R.id.accounts_sign_up_btn);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createFirebaseAccountsUI();
            }
        });
    }

    /**
     * Set the logged in layout to be visible in accounts fragment
     *
     * @return     null
     */
    public void setLoggedInLayoutVisible() {
        mLoggedInLayout.setVisibility(View.VISIBLE);
        mNotLoggedInLayout.setVisibility(View.GONE);
    }

    /**
     * Set the Not logged in layout to be visible in accounts fragment
     *
     * @return     null
     */
    public void setNotLoggedInLayoutVisible() {
        mLoggedInLayout.setVisibility(View.GONE);
        mNotLoggedInLayout.setVisibility(View.VISIBLE);
    }

    /**
     * Set the Users Email and Name in the Accounts View
     *
     * @return     null
     */
    public void setUserNameEmailText() {
        mEmailTextView.setText(mCurrentUser.getEmail());
        mNameTextView.setText(mCurrentUser.getName());
    }
}
