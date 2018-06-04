package com.mad.dway.model.directions;

import java.util.ArrayList;

/**
 * Directions model which provides turn by turn navigation instruction steps in a text format
 *
 * @author  12934713
 * @version 1.0
 */

public class Directions {
    private ArrayList<String> mDirectionInstructions;

    public Directions() {
        mDirectionInstructions = new ArrayList<>();
    }

    public ArrayList<String> getDirectionInstructions() {
        return mDirectionInstructions;
    }

    public void setDirectionInstructions(ArrayList<String> directionInstructions) {
        mDirectionInstructions = directionInstructions;
    }

    public void pushDirectionStepIntoInstructions(String instruction) {
        mDirectionInstructions.add(instruction);
    }
}
