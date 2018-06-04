package com.mad.dway.presenter;

import android.support.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    private AccountsFragment mAccountsView;
    private List<AuthUI.IdpConfig> providers;

    public AccountsPresenter(AccountsFragment view) {
        mAccountsView = view;

        providers = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build());
    }

    public void createFirebaseAccountsUI() {
        mAccountsView.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                mAccountsView.RC_SIGN_IN);
    }


    public void logOutFirebaseAccounts() {
        AuthUI.getInstance()
                .signOut(mAccountsView.getContext())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        mAccountsView.setNotLoggedInLayoutVisible();
                    }
                });
    }
}
