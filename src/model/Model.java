package model;

public class Model {
    public String typeText = ""; // Text to be typed
    public Object lastIncorrectHighlight; // Indicates wrongly typed character, if any
    public int charPtr = 0; // index of character to be typed
    public long startTime = -1; // time at which player started typing
    public long gameDuration = 0;
    public int totalMistakes = 0;
}
