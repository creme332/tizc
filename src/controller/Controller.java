package controller;

import java.awt.event.*;
import model.Model;
import view.*;
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
    private Action restartGameAction;

    public Controller() {
        // TODO: not a good idea to make name of screen set by user
        frame.addToScreenContainer(homeScreenController.getHomeScreen(), "homeScreen");
        frame.addToScreenContainer(playScreenController.getPlayScreen(), "playScreen");
        frame.addToScreenContainer(gameOverController.getGameOverScreen(), "gameOverScreen");

        // listen to start button presses on home screen
        homeScreenController.addStartButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // when start button is clicked, show playScreen
                frame.setScreen("playScreen");
            }
        });

        restartGameAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                restartGame();
            }
        };
        gameOverController.addActionOnGameRestart(restartGameAction);
        gameOverController.addTabAction(restartGameAction);

        playScreenController.addPropertyChangeListener(this);

        frame.showFrame();

    }

    private void restartGame() {
        playScreenController.initialise();
        frame.setScreen("playScreen");
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        String propertyName = e.getPropertyName();
        System.out.println(":rere");
        if ("gameOver".equals(propertyName)) {
            gameOverController.showStats();
            frame.setScreen("gameOverScreen");

        }

    }

}
