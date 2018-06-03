package com.mad.dway.model;

import android.util.Log;

import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.Fare;

import java.util.ArrayList;

/**
 * Created by ang on 3/6/18.
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

    public static ArrayList<String> getDirectionsFromAtoB(CurrentLocation locationA, Place locationB) {
        String locationALatLng = String.valueOf(locationA.getLatitude())
                + ","
                + String.valueOf(locationA.getLongitude());
        String locationBLatLng = String.valueOf(locationB.getLatitude())
                + ","
                + String.valueOf(locationB.getLongitude());

        DirectionsRoute[] result = mDirectionsEndPoint.getDirectionsFromAtoB(locationALatLng, locationBLatLng);

        DirectionsLeg leg = result[0].legs[0];

        for (int i = 0; i < leg.steps.length; i++) {
            DirectionsStep step = result[0].legs[0].steps[i];
            String htmlInstruction = step.htmlInstructions;
            String textInstruction = fixTextFormattingInstruction(htmlInstruction);
            mDirections.pushDirectionStepIntoInstructions(textInstruction);
        }


//        for (int i = 0; i < result.length; i++) {
//
//            Log.d("routes legs", String.valueOf(result[i].legs));
//            for (int j = 0; j < result[i].legs.length; j++) {
//                Log.d("route each leg", String.valueOf(result[i].legs[j].steps));
//                for (int l = 0; l < result[i].legs[j].steps.length; l++) {
//                    Log.d("route each leg", String.valueOf(result[i].legs[j].steps[l].htmlInstructions));
//                }
//            }
//            Log.d("routes waypoint order", String.valueOf(result[i].waypointOrder));
//            Log.d("routes waypoint order", String.valueOf(result[i].waypointOrder.length));
//            for (int k = 0; k < result[i].waypointOrder.length; k++) {
//                Log.d("route each leg", String.valueOf(result[i].waypointOrder[k]));
//            }
//            Fare fare = result[i].fare;
//            Log.d("fare", String.valueOf(fare));
//        }

        return mDirectionsInstructions;
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
