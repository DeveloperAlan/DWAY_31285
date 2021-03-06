package com.mad.dway.model.places;

import android.util.Log;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.TextSearchRequest;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

/**
 * Places End point to communicate with the Google Web services API to get places close to what the
 * user has searched or what the application explore feature has searched
 *
 * @author  12934713
 * @version 1.0
 */

public class PlacesEndPoint {
    private static String PLACES_API_KEY = "AIzaSyBNw8N_JdijSoRQQqUR-6HPkV5Z37YkWPQ";
    private static PlacesEndPoint mPlacesEndPoint;
    private static GeoApiContext mContext;
    private static PlacesSearchResult[] mSearchedResult;

    private PlacesEndPoint() {

    }

    /**
     * Get or create the instance of the places end point
     *
     * @return the instance of the placesEndPoint
     */
    public static PlacesEndPoint getInstance() {
        if (mPlacesEndPoint == null) {
            mPlacesEndPoint = new PlacesEndPoint();
            mContext = new GeoApiContext.Builder().apiKey(PLACES_API_KEY).build();
        }
        return mPlacesEndPoint;
    }

    /**
     * Get the search result of the place that the user has requested and give them back the result
     * Synchronous request
     *
     * @param searchQuery
     * @return the result of the places
     */
    public static PlacesSearchResult[] searchPlace(String searchQuery) {
        TextSearchRequest request = PlacesApi.textSearchQuery(mContext, searchQuery);
        Log.d("search place working", "working?");

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
