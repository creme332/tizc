package view;

import java.awt.*;
import javax.swing.*;

/**
 * Frame of the GUI application.
 */
public class Frame extends JFrame {
    // frame properties
    private int frameWidth = 1000;
    private int frameHeight = 800;

    // screens
    JPanel screenContainer = new JPanel(); // a container for all screens
    private CardLayout cl = new CardLayout(); // used to swap between screens
    private String currentScreen; // screen which is currently displayed

    public Frame() {
        this.setTitle("tizc"); // frame title

        // set frame properties
        this.setSize(frameWidth, frameHeight);
        this.setLocationRelativeTo(null); // center frame on startup
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // add close button to frame

        screenContainer.setLayout(cl);

        this.add(screenContainer);
    }

    /**
     * 
     * @return Unique name of screen currently being displayed: "homeScreen",
     *         "playScreen", or
     *         "gameOverScreen"
     */
    public String getScreen() {
        return currentScreen;
    }

    /**
     * Displays frame which is initially hidden.
     * 
     * Call this function once all components have been added to the frame
     * to ensure proper rendering.
     */
    public void showFrame() {
        this.setVisible(true);
    }

    /**
     * Changes from one screen to another.
     * 
     * If screen name is invalid, error is output in console.
     * 
     * @param newWindow name of new screen: "homeScreen", "playScreen", or
     *                  "gameOverScreen"
     */
    public void setScreen(String newWindow) {
        if (newWindow != HomeScreen.name && newWindow != GameOverScreen.name && newWindow != PlayScreen.name) {
            System.out.println("Invalid screen name");
            return;
        }
        if (newWindow == currentScreen) {
            return;
        }
        currentScreen = newWindow;
        cl.show(screenContainer, currentScreen);
    }

    /**
     * Adds a different screen to frame.
     * 
     * @param screen
     * @param name
     */
    public void addToScreenContainer(Component screen, String name) {
        screenContainer.add(screen, name);
        this.validate();
        this.repaint();
    }
}
