public class Model {
    String typeText = ""; // Text to be typed
    Object lastIncorrectHighlight; // Indicates wrongly typed character, if any
    int charPtr = 0; // index of character to be typed
    long startTime = -1; // time at which player started typing
    long gameDuration = 0;
    int totalMistakes = 0;
}
