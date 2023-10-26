package controller;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import view.GameOverScreen;
import model.Model;

public class GameOverController {
    private GameOverScreen gameOverScreen = new GameOverScreen();
    private Model model;

    public GameOverController(Model model) {
        this.model = model;
    }

    public void addActionOnGameRestart(ActionListener action) {
        gameOverScreen.addRestartButtonListener(action);
    }

    public void addTabAction(Action tabAction) {
        String TAB_PRESS = "tabpress"; // ! TAB_PRESS should not contain capital letters
        gameOverScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), TAB_PRESS);
        gameOverScreen.getActionMap().put(TAB_PRESS, tabAction);
    }

    /**
     * Calculates the typing speed in WPM (words per minute) of a player
     * 
     * @param charCount total number of characters (including spaces) in text typed
     * @param timeTaken time taken in seconds by player
     * @return WPM
     */
    private long getWPM(long charCount, long timeTaken) {
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
    private long getAccuracy(long charCount, long mistakeCount) {
        if (charCount > 0)
            return 100 * (charCount - mistakeCount) / charCount;
        return 0;

    }

    /**
     * Display game statistics: game duration, WPM, accuracy
     */
    public void showStats() {
        gameOverScreen.setTimeTaken(model.getGameDuration()); // display game duration
        gameOverScreen.setWPM(getWPM(model.getTypeText().length(), model.getGameDuration())); // display wpm
        gameOverScreen.setAccuracy(getAccuracy(model.getTypeText().length(), model.getTotalMistakes())); // display
                                                                                                         // accuracy
    }

    public GameOverScreen getGameOverScreen() {
        return gameOverScreen;
    }
}
