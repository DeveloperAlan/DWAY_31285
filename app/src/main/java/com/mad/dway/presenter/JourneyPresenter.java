package com.mad.dway.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mad.dway.R;
import com.mad.dway.model.Place;
import com.mad.dway.model.PlacesEndPoint;
import com.mad.dway.model.SearchedPlaces;
import com.mad.dway.view.fragments.JourneyFragment;
import com.mad.dway.view.fragments.SearchResultsFragment;

import java.util.ArrayList;

/**
 * Created by ang on 28/5/18.
 */

public class JourneyPresenter {

    private JourneyFragment mJourneyFragment;
    private FragmentManager mFragmentManager;
    private PlacesEndPoint mPlacesApiEndPoint;
    private SearchedPlaces mSearchedPlaces;
    private Fragment mSearchResult;
    private Fragment mLobbyFragment;
    private EditText mSearchEditText;
    private View mView;
    private Button mSearchButton;


    public JourneyPresenter(JourneyFragment view) {
        mJourneyFragment = view;
        mFragmentManager = mJourneyFragment.getFragmentManager();
        mView = mJourneyFragment.getView();
        mPlacesApiEndPoint = PlacesEndPoint.getInstance();
        mSearchedPlaces = SearchedPlaces.getInstance();
        mSearchEditText = mView.findViewById(R.id.fragment_journey_search_edit_text);
        mSearchButton = mView.findViewById(R.id.destination_search_button);
        addSearchButtonListener();
    }

    public void addResultsDropdown() {
        if (checkIfResultsExist()) {
            removeResultsDropdown();
        }
        if (mSearchedPlaces.getPlaces() != null) {
            FragmentTransaction fragTransaction = mFragmentManager.beginTransaction();

            mSearchResult = new SearchResultsFragment();
            fragTransaction.add(R.id.linear_layout_search_result, mSearchResult , "search_results");
            fragTransaction.commit();
        }
    }

    public void addLobbyFragment() {
        FragmentTransaction fragTransaction = mFragmentManager.beginTransaction();

        mLobbyFragment = new LobbyFragment();
    }

    public void removeResultsDropdown() {
        if (!checkIfResultsExist()) return;
        Fragment fragment = mFragmentManager.findFragmentById(R.id.linear_layout_search_result);
        FragmentTransaction fragTransaction = mFragmentManager.beginTransaction();

        fragTransaction.remove(fragment).commit();
    }

    public boolean checkIfResultsExist() {
        Fragment fragment = mFragmentManager.findFragmentById(R.id.linear_layout_search_result);
        if (fragment == null) {
            return false;
        }
        return true;
    }

    public void searchForResults() {
        mSearchedPlaces.setPlaces(SearchedPlaces.searchPlace(String.valueOf(mSearchEditText.getText())));
        addResultsDropdown();
    }

    public void addSearchButtonListener() {
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchForResults();
            }
        });
    }

}
