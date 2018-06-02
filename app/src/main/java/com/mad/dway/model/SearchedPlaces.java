package com.mad.dway.model;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.model.Photo;
import com.google.maps.model.PlacesSearchResult;

import java.util.ArrayList;

/**
 * Created by ang on 2/6/18.
 */

public class SearchedPlaces {
    private static SearchedPlaces mInstance;
    private static PlacesEndPoint mPlacesEndPoint;
    private static ArrayList<Place> mSearchedPlaces;

    private SearchedPlaces() {
    }

    public static SearchedPlaces getInstance() {
        if (mInstance == null) {
            mInstance = new SearchedPlaces();
            mPlacesEndPoint = PlacesEndPoint.getInstance();
            mSearchedPlaces = new ArrayList<>();
        }
        return mInstance;
    }

    public static ArrayList<Place> searchPlace(String searchQuery) {
        PlacesSearchResult[] results = mPlacesEndPoint.searchPlace(searchQuery);
        Log.d("something", String.valueOf(results));
        if (results != null) {
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
