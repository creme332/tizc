package com.github.creme332.view;

import java.awt.*;
import javax.swing.*;

import com.github.creme332.utils.IconLoader;
import com.github.creme332.view.GameOver.GameOverScreen;
import com.github.creme332.view.Settings.Form;

/**
 * Frame of the GUI application.
 */
public class Frame extends JFrame {
    // frame properties
    private int frameWidth = 1600;
    private int frameHeight = 1000;

    // screens
    JPanel screenContainer = new JPanel(); // a container for all screens
    private CardLayout cl = new CardLayout(); // used to swap between screens
    private String currentScreen; // screen which is currently displayed

    public Frame() {

        ImageIcon img = new ImageIcon();

        try {
            img = new IconLoader().loadIcon("/icon/logo-32x32.png");
        } catch (Exception e) {
            System.out.println(e);
        }

        this.setIconImage(img.getImage());

        this.setTitle("tizc"); // set frame title

        // set frame size
        this.setSize(frameWidth, frameHeight);

        // make frame resizable
        this.setResizable(true);

        // add close button to frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ! Maximize frame on startup may cause a white screen to appear.
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH); // maximize frame on startup

        // center frame on startup if frame is not maximized
        if (this.getExtendedState() != JFrame.MAXIMIZED_BOTH) {
            // ! Do not set locationRelative to null if screen is maximized
            this.setLocationRelativeTo(null);
        }

        screenContainer.setLayout(cl);

        this.add(screenContainer);
    }

    /**
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
        if (newWindow != HomeScreen.name && newWindow != GameOverScreen.name && newWindow != PlayScreen.name
                && newWindow != Form.name) {
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
