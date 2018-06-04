package com.mad.dway.model.places;

import android.util.Log;

import com.google.maps.model.PlacesSearchResult;

import java.util.ArrayList;

/**
 * Repository to organise retrival of information related to searching places, using the places end
 * point to search for particular locations and also to save the locations in memory
 *
 * @author  12934713
 * @version 1.0
 */

public class SearchedPlacesRepository {
    private static SearchedPlacesRepository mInstance;
    private static PlacesEndPoint mPlacesEndPoint;
    private static ArrayList<Place> mSearchedPlaces;

    private SearchedPlacesRepository() {
    }

    public static SearchedPlacesRepository getInstance() {
        if (mInstance == null) {
            mInstance = new SearchedPlacesRepository();
            mPlacesEndPoint = PlacesEndPoint.getInstance();
            mSearchedPlaces = new ArrayList<>();
        }
        return mInstance;
    }

    public static ArrayList<Place> searchPlace(String searchQuery) {
        PlacesSearchResult[] results = mPlacesEndPoint.searchPlace(searchQuery);
        Log.d("something", String.valueOf(results));
        if (results != null) {
            mSearchedPlaces = new ArrayList<>();
            for (int i = 0; i < results.length; i++) {
                PlacesSearchResult result = results[i];
                Log.i("search result length", String.valueOf(result.geometry));
                Log.i("search result length", String.valueOf(result.geometry.location));
                Log.i("search result length", String.valueOf(result.geometry.location.lat));
                Log.i("search result length", String.valueOf(result.geometry.location.lng));
                Log.i("search result length", String.valueOf(result.name));
                Log.i("search result length", String.valueOf(result.formattedAddress));
                Place resultPlace = new Place(
                        result.name,
                        result.geometry.location.lat,
                        result.geometry.location.lng,
                        result.formattedAddress,
                        result.photos);
                Log.d("result name", resultPlace.getName());
                mSearchedPlaces.add(resultPlace);
            }
        }
        return mSearchedPlaces;
    }

    public ArrayList<Place> getPlaces() {
        return mSearchedPlaces;
    }

    public void setPlaces(ArrayList<Place> places) {
        mSearchedPlaces = places;
    }

}
