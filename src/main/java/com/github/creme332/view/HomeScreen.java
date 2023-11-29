package com.github.creme332.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.github.creme332.utils.PoppinsFont;

/**
 * First screen that is shown to user when app starts.
 */
public class HomeScreen extends JPanel {
    private JButton startGameButton = new JButton("Play");
    private JButton settingsButton = new JButton();
    public static String name = "homeScreen";

    public HomeScreen() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel header = new JPanel();
        JPanel body = new JPanel();
        body.setLayout(new GridBagLayout());
        body.setOpaque(false);
        header.setOpaque(false);

        Font myFont = new PoppinsFont().Black;

        ImageIcon settingsIcon = new ImageIcon(
                new ImageIcon(this.getClass().getResource("/icon/settings.png")).getImage().getScaledInstance(50,
                        50,
                        Image.SCALE_DEFAULT));

        // remove default styles from button
        settingsButton.setFocusPainted(false);
        settingsButton.setOpaque(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setForeground(Color.WHITE);
        settingsButton.setBorderPainted(false);

        settingsButton.setLocation(20, 20);

        // set icon to settings button
        settingsButton.setIcon(settingsIcon);

        header.add(settingsButton);
        this.add(header);

        // set style for Play button
        startGameButton.setPreferredSize(new Dimension(200, 100));
        startGameButton.setFont(myFont.deriveFont(30f));
        startGameButton.setFocusPainted(false);
        startGameButton.setOpaque(false);
        startGameButton.setContentAreaFilled(false);
        startGameButton.setForeground(Color.WHITE);
        startGameButton.setBorderPainted(false);

        body.add(startGameButton);
        this.add(body);
    }

    /***
     * Add action listener to start button
     * 
     * @param newActionListener
     */
    public void addStartButtonListener(ActionListener newActionListener) {
        startGameButton.addActionListener(newActionListener);
    }

    /***
     * Add action listener to start button
     * 
     * @param newActionListener
     */
    public void addSettingsButtonListener(ActionListener newActionListener) {
        settingsButton.addActionListener(newActionListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // add background image
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bg1.jpg"));

        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
