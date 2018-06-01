package com.mad.dway.model;

import android.util.Log;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.Fare;

/**
 * Created by ang on 28/5/18.
 */

public class DirectionsEndPoint {
    private static String DIRECTIONS_API_KEY = "AIzaSyBNw8N_JdijSoRQQqUR-6HPkV5Z37YkWPQ";
    private static DirectionsEndPoint mDirectionsEndPoint = null;

    private DirectionsEndPoint() {

    }

    public static DirectionsEndPoint getInstance() {
        if (mDirectionsEndPoint == null) {
            mDirectionsEndPoint = new DirectionsEndPoint();
        }
        return mDirectionsEndPoint;
    }

    public static void doExample() {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(DIRECTIONS_API_KEY)
                .build();
        DirectionsApiRequest request =  DirectionsApi.getDirections(context, "Dundas New South Wales 2117, Australia", "Central+Sydney");
        Log.d("results for location", String.valueOf(request));
        request.setCallback(new PendingResult.Callback<DirectionsResult>() {
            @Override
            public void onResult(DirectionsResult result) {
                DirectionsRoute[] route = result.routes;
                Log.d("routes[]", String.valueOf(route));
                Log.d("routes[0]", String.valueOf(route.length));
                Fare fare = route[0].fare;
                Log.d("fare", String.valueOf(fare));

            }

            @Override
            public void onFailure(Throwable e) {
                // Handle error.
            }
        });
    }
}
