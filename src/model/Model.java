package model;

import utils.WordGenerator;

/**
 * Stores game state.
 * 
 */
public class Model {
    public String typeText = (new WordGenerator(10)).getString(); // Text to be typed
    public Object lastIncorrectHighlight;
    /**
     * lastIncorrectHighlight:
     * 
     * Highlighter object from swing. It stores the return value
     * of the `highlightChar` function in PlayScreen when
     * a character is wrongly typed.
     */
    public int charPtr = 0; // index of character to be typed (cursor position)
    public long startTime = -1; // time, in ms, at which player started typing
    public long gameDuration = 0; // number of seconds elapsed since first chracter was typed
    public int totalMistakes = 0; // number of times a character was wrongly typed
}
