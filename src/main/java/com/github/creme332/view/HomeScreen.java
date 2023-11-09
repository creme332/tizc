package com.github.creme332.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.github.creme332.utils.PoppinsFont;

/**
 * First screen that is shown to user when app starts.
 */
public class HomeScreen extends JPanel {
    private JButton startGameButton = new JButton("Play");
    public static String name = "homeScreen";

    public HomeScreen() {
        this.setLayout(new GridBagLayout());

        Font myFont = new PoppinsFont().Black;

        // set style for Play button
        startGameButton.setPreferredSize(new Dimension(200, 100));
        startGameButton.setFont(myFont.deriveFont(30f));
        startGameButton.setFocusPainted(false);
        startGameButton.setOpaque(false);
        startGameButton.setContentAreaFilled(false);
        startGameButton.setForeground(Color.WHITE);
        startGameButton.setBorderPainted(false);

        this.add(startGameButton);
    }

    /***
     * Add action listener to start button
     * 
     * @param newActionListener
     */
    public void addStartButtonListener(ActionListener newActionListener) {
        startGameButton.addActionListener(newActionListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // add background image
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bg1.jpg"));

        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
