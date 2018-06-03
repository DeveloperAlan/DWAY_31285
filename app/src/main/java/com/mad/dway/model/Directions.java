package com.mad.dway.model;

import java.util.ArrayList;

/**
 * Created by ang on 3/6/18.
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
