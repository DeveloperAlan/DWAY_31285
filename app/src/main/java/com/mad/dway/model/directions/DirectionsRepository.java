package com.mad.dway.model.directions;

import android.os.AsyncTask;

import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.mad.dway.model.location.CurrentLocationRepository;
import com.mad.dway.model.places.Place;

import java.util.ArrayList;

/**
 * Directions Repository provides a communication gateway to the Directions model and Api endpoint
 * as well as organising both the model and api into one package that can be accessed via this repo
 * without using the model and api directly
 *
 * @author  12934713
 * @version 1.0
 */

public class DirectionsRepository {
    private static DirectionsEndPoint mDirectionsEndPoint;
    private static Directions mDirections;
    private static DirectionsRepository mDirectionsRepository;
    private static ArrayList<String> mDirectionsInstructions;

    private DirectionsRepository() {

    }

    public static DirectionsRepository getInstance() {
        if (mDirectionsRepository == null) {
            mDirectionsRepository = new DirectionsRepository();
            mDirectionsEndPoint = DirectionsEndPoint.getInstance();
            mDirections = new Directions();
        }

        return mDirectionsRepository;
    }

    public static Directions getDirections() {
        return mDirections;
    }

    public static void setDirections(Directions mDirections) {
        DirectionsRepository.mDirections = mDirections;
    }

    public static void getDirectionsFromAtoB(CurrentLocationRepository locationA, Place locationB) {
        String locationALatLng = String.valueOf(locationA.getLatitude())
                + ","
                + String.valueOf(locationA.getLongitude());
        String locationBLatLng = String.valueOf(locationB.getLatitude())
                + ","
                + String.valueOf(locationB.getLongitude());

//        DirectionsRoute[] result = mDirectionsEndPoint.getDirectionsFromAtoB(locationALatLng, locationBLatLng);

        new GetDirectionsTask().execute(locationALatLng, locationBLatLng);

    }

    private static class GetDirectionsTask extends AsyncTask<String, Void, DirectionsRoute[]> {

        @Override
        protected DirectionsRoute[] doInBackground(String... strings) {
            DirectionsRoute[] result = mDirectionsEndPoint.getDirectionsFromAtoB(strings[0], strings[1]);
            return result;
        }

        @Override
        protected void onPostExecute(DirectionsRoute[] directionsRoutes) {
            super.onPostExecute(directionsRoutes);

            DirectionsLeg leg = directionsRoutes[0].legs[0];

            for (int i = 0; i < leg.steps.length; i++) {
                DirectionsStep step = directionsRoutes[0].legs[0].steps[i];
                String htmlInstruction = step.htmlInstructions;
                String textInstruction = fixTextFormattingInstruction(htmlInstruction);
                mDirections.pushDirectionStepIntoInstructions(textInstruction);
            }
        }
    }

    private static String fixTextFormattingInstruction(String instruction) {
        String removalOpeningBreakBrace = "<b>";
        String removalClosingBreakBrace = "</b>";
        String removalClosingDivBrace = "</div>";
        String removalOpeningDivBrace = "<div style=\"font-size:0.9em\">";
        String result;

        result = instruction.replace(removalOpeningBreakBrace, "");
        result = result.replace(removalClosingBreakBrace, "");
        result = result.replace(removalOpeningDivBrace, "");
        result = result.replace(removalClosingDivBrace, "");

        return result;
    }


}
