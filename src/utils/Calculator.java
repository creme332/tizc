package utils;

/**
 * Calculates game statistics
 */
public class Calculator {

    /**
     * Calculates the typing speed in WPM (words per minute) of a player
     * 
     * @param charCount total number of characters (including spaces) in text typed
     * @param timeTaken time taken in seconds by player
     * @return WPM
     */
    public double getWPM(long charCount, long timeTaken) {
        long secondsInMinute = 60;
        long charPerWord = 5; // average number of characters in a word
        if (timeTaken > 0) {
            return (secondsInMinute * charCount / (charPerWord * timeTaken));
        }
        return 0;
    }

    /**
     * Calculates the accuracy of a player
     * 
     * @param charCount    total number of characters (including spaces) in text
     *                     typed
     * @param mistakeCount number of characters wrongly typed
     * @return A percentage (0-100)
     */
    public long getAccuracy(long charCount, long mistakeCount) {
        if (charCount > 0)
            return 100 * (charCount - mistakeCount) / charCount;
        return 0;
    }

}
