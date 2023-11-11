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
    private long gameDuration = 0; // game duration in seconds

    // create arrays to store pairs (time, wpm)
    ArrayList<Double> timeArray = new ArrayList<Double>();
    ArrayList<Double> wpmArray = new ArrayList<Double>();

    /**
     * Resets model to its intial state.
     */
    public void reset() {
        typeText = (new WordGenerator()).getRandomText();
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

    /**
     * 
     * @return Text that user will type
     */
    public String getTypeText() {
        return typeText;
    }

    /**
     * 
     * @return game duration in seconds
     */
    public long getGameDuration() {
        return gameDuration;
    }

    /**
     * Sets game duration to a non-negative value
     * 
     * @param duration duration in seconds. Must be non-negative.
     */
    public void setGameDuration(long duration) {
        if (duration >= 0)
            gameDuration = duration;
    }

    /**
     * Returns the current character that user needs to type.
     * 
     * @return character
     */
    public char getCurrentChar() throws Exception {
        // check if typeText is not initialised
        if (typeText == null || typeText.length() == 0)
            throw new Exception("typeText has not been initialised");

        // check if chartPtr is valid
        if (charPtr >= typeText.length() || charPtr < 0)
            throw new Exception("charPtr is out of bounds");

        return typeText.charAt(charPtr);
    }

    /**
     * Save current time to start time.
     */
    public void initStartTime() {
        startTime = System.currentTimeMillis();
    }

    /**
     * 
     * @return time in milliseconds when game started. A negative value
     *         indicates that game has not started.
     */
    public long getStartTime() {
        return startTime;
    }

    public void setTypeText(String newTypeText) {
        typeText = newTypeText;
    }

    /**
     * Points to the next chracter to be typed. If no more characters to be typed,
     * cursor = length of text
     */
    public void incrementCursor() {
        charPtr = Math.min(charPtr + 1, getTypeText().length());
    }

    /**
     * 
     * @return Zero-based index of character to be typed.
     */
    public int getCursorPos() {
        return charPtr;
    }

    /**
     * Add 1 to the total number of mistakes made by user when typing
     */
    public void incrementMistakes() {
        totalMistakes++;
    }

    /**
     * 
     * @return Number of times the wrong character was typed.
     */
    public int getTotalMistakes() {
        return totalMistakes;
    }

    /**
     * 
     * @return highlight object for incorrectly typed character.
     *         If no character was incorrectly typed, value is null.
     */
    public Object getBadHighlight() {
        return badHighlight;
    }

    /**
     * Save the highlight object for the incorrectly typed character
     * 
     * @param highlight
     */
    public void setBadHighlight(Object highlight) {
        badHighlight = highlight;
    }

    /**
     * Records WPM at a given time
     * 
     * @param currentSecond time elapsed in seconds since game started
     * @param wpm           words per minute
     */
    public void recordWPM(double currentSecond, double wpm) throws Exception {
        if (currentSecond < 0)
            throw new Exception("currentSecond cannot be negative");

        if (wpm < 0)
            throw new Exception("wpm cannot be negative");

        timeArray.add(currentSecond);
        wpmArray.add(wpm);
    }

    /**
     * 
     * @return Array of times at which WPM was recorded for a game session
     */
    public double[] getTimeArray() {
        return timeArray.stream().mapToDouble(Double::doubleValue).toArray();
    }

    /**
     * 
     * @return Array of WPM recorded for a game session
     */
    public double[] getWPMArray() {
        return wpmArray.stream().mapToDouble(Double::doubleValue).toArray();
    }
}
