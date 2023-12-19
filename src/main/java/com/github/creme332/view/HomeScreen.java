package com.github.creme332.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.kordamp.ikonli.bootstrapicons.BootstrapIcons;
import org.kordamp.ikonli.swing.FontIcon;

import com.github.creme332.utils.IconLoader;

/**
 * First screen that is shown to user when app starts.
 */
public class HomeScreen extends JPanel {
    private JButton startGameButton = new JButton("Play");
    private JButton settingsButton = new JButton();
    public static String name = "homeScreen";

    public HomeScreen() {
        this.setLayout(new BorderLayout());

        JPanel header = new JPanel();
        JPanel body = new JPanel();
        header.setLayout(new BorderLayout());
        body.setLayout(new GridBagLayout());
        body.setOpaque(false);
        header.setOpaque(false);

        // remove default styles from button
        settingsButton.setFocusPainted(false);
        settingsButton.setOpaque(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setForeground(Color.WHITE);
        settingsButton.setBorderPainted(false);

        settingsButton.setLocation(20, 20);

        // set icon to settings button
        FontIcon icon = FontIcon.of(BootstrapIcons.GEAR, 50, Color.white);
        settingsButton.setIcon(icon);

        header.add(settingsButton, BorderLayout.EAST);
        this.add(header, BorderLayout.PAGE_START);

        // set style for Play button
        startGameButton.setPreferredSize(new Dimension(200, 100));
        startGameButton.putClientProperty("FlatLaf.style", "font: $h1.font");
        startGameButton.setOpaque(false);
        startGameButton.setContentAreaFilled(false);
        startGameButton.setForeground(Color.WHITE);
        startGameButton.setBorderPainted(false);

        body.add(startGameButton);
        this.add(body, BorderLayout.CENTER);
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
        ImageIcon img = new ImageIcon();

        try {
            img = new IconLoader().loadIcon("/bg1.jpg");
        } catch (Exception e) {
            System.out.println(e);
        }

        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
