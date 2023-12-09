package com.github.creme332.controller;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import com.github.creme332.model.Model;
import com.github.creme332.utils.Calculator;
import com.github.creme332.view.GameOver.GameOverScreen;

/**
 * Controller for game over screen
 */
public class GameOverScreenController {
    private GameOverScreen gameOverScreen = new GameOverScreen();
    private Calculator calc = new Calculator();
    private Model model;

    public GameOverScreenController(Model model) {
        this.model = model;
    }

    /**
     * Add action when restart button is pressed
     * 
     * @param action
     */
    public void addActionOnGameRestart(ActionListener action) {
        gameOverScreen.addRestartButtonListener(action);
    }

    /**
     * Add action when home button is pressed
     * 
     * @param action
     */
    public void addActionForHomeButton(ActionListener action) {
        gameOverScreen.addHomeButtonListener(action);
    }

    /**
     * Add an action when tab is pressed
     * 
     * @param tabAction
     */
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
        // display chart
        gameOverScreen.drawChart(model.getTimeArray(),
                model.getWPMArray());

        // display game duration
        gameOverScreen.setTimeTaken(model.getGameDuration());

        // display cpm
        double cpm = calc.cpm(
                model.getTypeText().length(),
                model.getGameDuration());
        gameOverScreen.setCPM((long) cpm);

        // display final wpm
        double wpm = calc.wpm(
                model.getTypeText().length(),
                model.getGameDuration());
        gameOverScreen.setWPM((long) wpm);

        // display accuracy
        gameOverScreen.setAccuracy(
                (long) calc.accuracy(
                        model.getTypeText().length(),
                        model.getTotalMistakes()));
    }

    public GameOverScreen getGameOverScreen() {
        return gameOverScreen;
    }
}
