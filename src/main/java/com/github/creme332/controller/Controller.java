package com.github.creme332.controller;

import java.awt.event.*;
import com.github.creme332.model.Model;
import com.github.creme332.view.*;
import java.beans.*;

import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * Controls all the logic in the application by linking views and model.
 */
public class Controller implements PropertyChangeListener {
    private Model model = new Model(); // game state
    private Frame frame = new Frame();
    private HomeScreenController homeScreenController = new HomeScreenController();
    private PlayScreenController playScreenController = new PlayScreenController(model);
    private GameOverController gameOverController = new GameOverController(model);

    public Controller() {
        // insert screens to frame
        frame.addToScreenContainer(homeScreenController.getHomeScreen(), HomeScreen.name);
        frame.addToScreenContainer(playScreenController.getPlayScreen(), PlayScreen.name);
        frame.addToScreenContainer(gameOverController.getGameOverScreen(), GameOverScreen.name);

        // set home screen as default screen
        frame.setScreen(HomeScreen.name);

        // listen to start button presses on home screen
        homeScreenController.addStartButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
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

        // Restart game when Tab key is pressed
        gameOverController.addActionOnGameRestart(restartGameAction);
        gameOverController.addTabAction(restartGameAction);

        // playScreenController determines when game is over. Listen to it.
        playScreenController.addGameOverListener(this);

        frame.showFrame();
    }

    /**
     * Restarts game and displays PlayScreen
     */
    private void restartGame() {
        playScreenController.initialiseGame();
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
