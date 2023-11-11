package com.github.creme332.utils;

/**
 * Calculates game statistics
 */
public class Calculator {
    private long secondsInMinute = 60; // number of seconds in a minute
    private long charPerWord = 5; // average number of characters in a word

    /**
     * Calculates the typing speed in WPM (words per minute) of a player
     * 
     * @param charCount total number of characters (including spaces) in text typed.
     *                  This does not include any incorrectly typed characters.
     * @param timeTaken time taken in seconds by player
     * @return WPM
     */
    public double wpm(long charCount, long timeTaken) {
        return cpm(charCount, timeTaken) / charPerWord;
    }

    /**
     * Calculates the typing speed in CPM (characters per minute) of a player
     * 
     * @param charCount total number of characters (including spaces) in text typed.
     *                  This does not include any incorrectly typed characters.
     * @param timeTaken time taken in seconds by player
     * @return CPM
     */
    public double cpm(long charCount, long timeTaken) {
        if (timeTaken > 0) {
            return (secondsInMinute * charCount / timeTaken);
        }
        return 0;
    }

    /**
     * Calculates the accuracy of a player
     * 
     * @param charCount    total number of characters (including spaces) in text
     *                     typed
     * @param mistakeCount number of times incorrect characters wrongly typed
     * @return A percentage (0-100)
     */
    public long accuracy(long charCount, long mistakeCount) {
        if (charCount > 0)
            return 100 * (charCount - mistakeCount) / charCount;
        return 0;
    }

}
