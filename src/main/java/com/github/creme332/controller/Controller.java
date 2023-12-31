package com.github.creme332.controller;

import java.awt.event.*;
import com.github.creme332.model.Model;
import com.github.creme332.view.*;
import com.github.creme332.view.GameOver.GameOverScreen;
import com.github.creme332.view.Settings.Form;

import java.beans.*;

import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * Controls all the logic in the application by linking views and model.
 */
public class Controller implements PropertyChangeListener {
    private Model model = new Model(); // game state
    public Frame frame = new Frame(); // frame of app
    private HomeScreenController homeScreenController = new HomeScreenController();
    private PlayScreenController playScreenController = new PlayScreenController(model);
    private GameOverScreenController gameOverController = new GameOverScreenController(model);
    private SettingsScreenController settingsScreenController = new SettingsScreenController();

    public Controller() {
        // insert screens to frame
        frame.addToScreenContainer(homeScreenController.getHomeScreen(), HomeScreen.name);
        frame.addToScreenContainer(playScreenController.getPlayScreen(), PlayScreen.name);
        frame.addToScreenContainer(gameOverController.getGameOverScreen(), GameOverScreen.name);
        frame.addToScreenContainer(settingsScreenController.getSettingsScreen(), Form.name);

        // add listener for settings screen
        settingsScreenController.addExitButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // when exit button is clicked, show home screen
                frame.setScreen(HomeScreen.name);
            }
        });

        // set home screen as default screen
        frame.setScreen(HomeScreen.name);

        // listen to settings button click on home screen
        homeScreenController.addSettingsButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // when start button is clicked, show playScreen
                frame.setScreen(Form.name);
            }
        });

        // listen to start button presses on home screen
        homeScreenController.addStartButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // initialize game with latest settings
                playScreenController.initGame();

                // when start button is clicked, show playScreen
                frame.setScreen(PlayScreen.name);
            }
        });

        Action restartGameAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                restartGame();
            }
        };

        Action goHomeAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setScreen(HomeScreen.name);
            }
        };

        // Restart game when Tab key is pressed
        gameOverController.addActionOnGameRestart(restartGameAction);
        gameOverController.addTabAction(restartGameAction);

        gameOverController.addActionForHomeButton(goHomeAction);

        // playScreenController determines when game is over. Listen to it.
        playScreenController.addGameOverListener(this);

        frame.showFrame();
    }

    /**
     * Restarts game and displays PlayScreen
     */
    public void restartGame() {
        playScreenController.initGame();
        frame.setScreen(PlayScreen.name);
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        String propertyName = e.getPropertyName();
        // when game is over, display game over screen with game stats
        if ("gameOver".equals(propertyName)) {
            gameOverController.showStats();
            frame.setScreen(GameOverScreen.name);
        }
    }
}
