package com.github.creme332.model;

import com.github.creme332.utils.WordGenerator;
import java.util.ArrayList;

/**
 * Stores game state.
 * 
 */
public class Model {
    private String typeText; // Text to be typed
    private Object badHighlight;
    /**
     * badHighlight:
     * 
     * Highlighter object from swing. It stores the return value
     * of the `highlightChar` function in PlayScreen when
     * a character is wrongly typed.
     */
    private int charPtr = 0; // index of character to be typed (cursor position)
    private long startTime = -1; // time, in ms, at which player started typing
    private int totalMistakes = 0; // number of times a character was wrongly typed
    private long gameDuration = 0;

    // create arrays to store pairs (time, wpm)
    ArrayList<Double> timeArray = new ArrayList<Double>();
    ArrayList<Double> wpmArray = new ArrayList<Double>();

    /**
     * Resets model to its intial state.
     */
    public void reset() {
        typeText = (new WordGenerator(10)).getString();
        badHighlight = null;
        charPtr = 0;
        startTime = -1;
        totalMistakes = 0;
        gameDuration = 0;
        if (timeArray != null)
            timeArray.clear();
        if (wpmArray != null)
            wpmArray.clear();
    }

    public String getTypeText() {
        return typeText;
    }

    public long getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(long duration) {
        if (duration >= 0)
            gameDuration = duration;
    }

    public char getCurrentChar() {
        if (charPtr < typeText.length())
            return typeText.charAt(charPtr);
        return ' ';
    }

    public void initStartTime() {
        startTime = System.currentTimeMillis();
    }

    public long getStartTime() {
        return startTime;
    }

    public void incrementCursor() {
        charPtr++;
    }

    public int getCursorPos() {
        return charPtr;
    }

    /**
     * Add 1 to the total number of mistakes made by user when typing
     */
    public void incrementMistakes() {
        totalMistakes++;
    }

    public int getTotalMistakes() {
        return totalMistakes;
    }

    public Object getBadHighlight() {
        return badHighlight;
    }

    public void setBadHighlight(Object highlight) {
        badHighlight = highlight;
    }

    public void recordWPM(double currentSecond, double wpm) {
        timeArray.add(currentSecond);
        wpmArray.add(wpm);
    }

    public double[] getTimeArray() {
        return timeArray.stream().mapToDouble(Double::doubleValue).toArray();
    }

    public double[] getWPMArray() {
        return wpmArray.stream().mapToDouble(Double::doubleValue).toArray();
    }
}
