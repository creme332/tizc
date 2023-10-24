package controller;

import java.awt.event.*;
import model.Model;
import view.*;

/**
 * Controls all the logic in the application by linking views and model.
 */
public class Controller {
    private Model model = new Model(); // game state
    private Frame frame = new Frame();
    private HomeScreenController homeScreenController = new HomeScreenController();
    private PlayScreenController playScreenController = new PlayScreenController(model);
    private GameOverController gameOverController = new GameOverController(model);

    public Controller() {

        frame.add(homeScreenController.getHomeScreen(), "homeScreen");
        frame.add(playScreenController.getPlayScreen(), "playScreen");
        frame.add(gameOverController.getGameOverScreen(), "gameOverScreen");

        // listen to start button presses on home screen
        homeScreenController.addStartButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // when start button is clicked, show playScreen
                frame.setScreen("playScreen");
            }
        });

        frame.showFrame();
    }

}
