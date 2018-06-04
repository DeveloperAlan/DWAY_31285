package com.mad.dway.model.directions;

import android.util.Log;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.Fare;

import java.io.IOException;

/**
 * Directions End point provides a gateway from the application to the Google Maps Web Api
 * for turn by turn directions as well as visualized directions on the map
 *
 * @author  12934713
 * @version 1.0
 */

public class DirectionsEndPoint {

    // Api key for the Directions Endpoint
    private static String DIRECTIONS_API_KEY = "AIzaSyBNw8N_JdijSoRQQqUR-6HPkV5Z37YkWPQ";

    private static GeoApiContext mContext;
    private static DirectionsEndPoint mDirectionsEndPoint = null;
    private static DirectionsRoute[] mDirectionsRoute;

    private DirectionsEndPoint() {

    }

    public static DirectionsEndPoint getInstance() {
        if (mDirectionsEndPoint == null) {
            mDirectionsEndPoint = new DirectionsEndPoint();
            mContext = new GeoApiContext.Builder().apiKey(DIRECTIONS_API_KEY).build();
        }
        return mDirectionsEndPoint;
    }

    public static DirectionsRoute[] getDirectionsFromAtoB(String locationA, String locationB) {

        DirectionsApiRequest request = DirectionsApi.getDirections(mContext, locationA, locationB);

        try {
            DirectionsResult result = request.await();
            mDirectionsRoute = result.routes;
            Log.d("routes[]", String.valueOf(mDirectionsRoute));
            Log.d("routes[0]", String.valueOf(mDirectionsRoute.length));
            Log.d("routes summary", String.valueOf(mDirectionsRoute[0].summary));
            Log.d("routes legs", String.valueOf(mDirectionsRoute[0].legs));
            Log.d("routes waypoint order", String.valueOf(mDirectionsRoute[0].waypointOrder));
            Fare fare = mDirectionsRoute[0].fare;
            Log.d("fare", String.valueOf(fare));

        } catch (IOException e) {

        } catch (ApiException e) {

        } catch (InterruptedException e) {

        }

        return mDirectionsRoute;
    }
}
