package model;

import utils.WordGenerator;

/**
 * Stores game state.
 * 
 */
public class Model {
    private String typeText = (new WordGenerator(10)).getString(); // Text to be typed
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

    public void reset(){

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
}
