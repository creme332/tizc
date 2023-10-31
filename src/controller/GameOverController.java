package controller;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import view.GameOverScreen;
import model.Model;
import utils.Calculator;

public class GameOverController {
    private GameOverScreen gameOverScreen = new GameOverScreen();
    private Calculator calc = new Calculator();
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
     * Display game statistics: game duration, WPM, accuracy
     */
    public void showStats() {
        // display game duration
        gameOverScreen.setTimeTaken(model.getGameDuration());

        // display wpm
        gameOverScreen.setWPM(
                calc.getWPM(
                        model.getTypeText().length(),
                        model.getGameDuration()));

        // display accuracy
        gameOverScreen.setAccuracy(
                calc.getAccuracy(
                        model.getTypeText().length(),
                        model.getTotalMistakes()));
    }

    public GameOverScreen getGameOverScreen() {
        return gameOverScreen;
    }
}
