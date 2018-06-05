package com.mad.dway.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mad.dway.R;
import com.mad.dway.model.places.PlacesEndPoint;
import com.mad.dway.model.places.SearchedPlacesRepository;
import com.mad.dway.view.fragments.JourneySearchFragment;
import com.mad.dway.view.fragments.SearchResultsFragment;

/**
 * Journey Search Presenter for the fragment. Adds and removes the results dropdown as well as does
 * search for the fragment
 *
 * @author  12934713
 * @version 1.0
 */

public class JourneySearchPresenter {

    private static final String SEARCH_RESULTS_FRAG_TRANSACTION = "search_results";
    private static final String IS_RESULTS_EXIST_KEY = "do search results exist";

    private JourneySearchFragment mJourneySearchFragment;
    private FragmentManager mFragmentManager;
    private PlacesEndPoint mPlacesApiEndPoint;
    private SearchedPlacesRepository mSearchedPlaces;
    private Fragment mSearchResult;
    private Fragment mLobbyFragment;
    private EditText mSearchEditText;
    private View mView;
    private Button mSearchButton;


    public JourneySearchPresenter(JourneySearchFragment view) {
        mJourneySearchFragment = view;
        mFragmentManager = mJourneySearchFragment.getFragmentManager();
        mView = mJourneySearchFragment.getView();
        mPlacesApiEndPoint = PlacesEndPoint.getInstance();
        mSearchedPlaces = SearchedPlacesRepository.getInstance();
        mSearchEditText = mView.findViewById(R.id.fragment_journey_search_edit_text);
        mSearchButton = mView.findViewById(R.id.destination_search_button);
        addSearchButtonListener();
    }

    /**
     * Add search places results to the fragment so that user can click on to get location
     *
     * @return void
     */
    public void addResultsDropdown() {
        if (checkIfResultsExist()) {
            removeResultsDropdown();
        }
        if (mSearchedPlaces.getPlaces() != null) {
            FragmentTransaction fragTransaction = mFragmentManager.beginTransaction();

            mSearchResult = new SearchResultsFragment();
            fragTransaction.add(R.id.linear_layout_search_result, mSearchResult , SEARCH_RESULTS_FRAG_TRANSACTION);
            fragTransaction.commit();
        }
    }

    /**
     * Remove search places list from the fragment
     *
     * @return void
     */
    public void removeResultsDropdown() {
        if (!checkIfResultsExist()) {
            Log.d(IS_RESULTS_EXIST_KEY, "false");
            Toast.makeText(mJourneySearchFragment.getContext(), R.string.no_results_found, Toast.LENGTH_SHORT);
            return;
        }
        Log.d(IS_RESULTS_EXIST_KEY, "true");
        Fragment fragment = mFragmentManager.findFragmentById(R.id.linear_layout_search_result);
        FragmentTransaction fragTransaction = mFragmentManager.beginTransaction();

        fragTransaction.remove(fragment).commit();
    }

    /**
     * Check if the search results exist and not null
     *
     * @return void
     */
    public boolean checkIfResultsExist() {
        Fragment fragment = mFragmentManager.findFragmentById(R.id.linear_layout_search_result);
        if (fragment == null) {
            return false;
        }
        return true;
    }

    /**
     * Search for the place inserted by the user and add results dropdown when finished
     *
     * @return void
     */
    public void searchForResults() {
        mSearchedPlaces.setPlaces(SearchedPlacesRepository.searchPlace(String.valueOf(mSearchEditText.getText())));
        addResultsDropdown();
    }

    /**
     * Add search button listener so the user can search for places depending on query
     *
     * @return void
     */
    public void addSearchButtonListener() {
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchForResults();
            }
        });
    }

}
