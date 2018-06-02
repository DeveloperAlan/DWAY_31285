package com.mad.dway.model;

import android.util.Log;

import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.PlacesApi;
import com.google.maps.TextSearchRequest;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

import org.w3c.dom.Text;

/**
 * Created by ang on 28/5/18.
 */

public class PlacesEndPoint {
    private static String PLACES_API_KEY = "AIzaSyBNw8N_JdijSoRQQqUR-6HPkV5Z37YkWPQ";
    private static PlacesEndPoint mPlacesEndPoint;
    private static GeoApiContext mContext;
    private static PlacesSearchResult[] mSearchedResult;

    private PlacesEndPoint() {

    }

    public static PlacesEndPoint getInstance() {
        if (mPlacesEndPoint == null) {
            mPlacesEndPoint = new PlacesEndPoint();
            mContext = new GeoApiContext.Builder().apiKey(PLACES_API_KEY).build();
        }
        return mPlacesEndPoint;
    }

    public static PlacesSearchResult[] searchPlace(String searchQuery) {
        TextSearchRequest request = PlacesApi.textSearchQuery(mContext, searchQuery);
        Log.d("search place working", "working?");
//        request.setCallback(new PendingResult.Callback<PlacesSearchResponse>() {
//            @Override
//            public void onResult(PlacesSearchResponse result) {
//                mSearchedResult = result.results;
//                Log.d("search response", String.valueOf(result.results));
//                Log.d( "search result length", String.valueOf(result.results.length));
//
//                Log.i( "search result length", String.valueOf(result.results[0].geometry));
//                Log.i( "search result length", String.valueOf(result.results[0].geometry.location));
//                Log.i( "search result length", String.valueOf(result.results[0].geometry.location.lat));
//                Log.i( "search result length", String.valueOf(result.results[0].geometry.location.lng));
//                Log.i( "search result length", String.valueOf(result.results[0].name));
//                Log.i( "search result length", String.valueOf(result.results[0].formattedAddress));
//            }
//
//            @Override
//            public void onFailure(Throwable e) {
//                mSearchedResult = null;
//            }
//        });

        // Synchronous
        try {
            PlacesSearchResponse result = request.await();
            mSearchedResult = result.results;
            Log.d("search response", String.valueOf(result.results));
            Log.d( "search result length", String.valueOf(result.results.length));

            Log.i( "search result length", String.valueOf(result.results[0].geometry));
            Log.i( "search result length", String.valueOf(result.results[0].geometry.location));
            Log.i( "search result length", String.valueOf(result.results[0].geometry.location.lat));
            Log.i( "search result length", String.valueOf(result.results[0].geometry.location.lng));
            Log.i( "search result length", String.valueOf(result.results[0].name));
            Log.i( "search result length", String.valueOf(result.results[0].formattedAddress));
            // Handle successful request.
        } catch (Exception e) {
            // Handle error
        }
        return mSearchedResult;
    }
}
