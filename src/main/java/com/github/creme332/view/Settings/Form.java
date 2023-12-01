package com.github.creme332.view.Settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.github.creme332.utils.SettingsManager;

public class Form extends JPanel implements ActionListener {
    public static String name = "settingsScreen";
    private JButton exitButton = new JButton("Exit");

    private Map<String, String[]> gameSettings = new SettingsManager().getSettings();
    private GridBagLayout layout;

    public Form() {
        GridBagConstraints gbc = new GridBagConstraints();
        layout = new GridBagLayout();
        this.setLayout(layout);

        // set global gbc constraints
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;
        int column = 1;
        for (String settingName : gameSettings.keySet()) {
            Section section = new Section(settingName, gameSettings.get(settingName));
            gbc.gridx = column;
            gbc.gridy = row;
            gbc.gridwidth = 2;
            this.add(section, gbc);
            row++;
        }

        // add exit button
        gbc.gridx = column;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        exitButton.setPreferredSize(new Dimension(100, 100));
        exitButton.setFont(UIManager.getFont("h1.font"));
        this.add(exitButton, gbc);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int[][] dims = layout.getLayoutDimensions();
        g.setColor(Color.white);
        int x = 0;
        for (int add : dims[0]) {
            x += add;
            g.drawLine(x, 0, x, getHeight());
        }
        int y = 0;
        for (int add : dims[1]) {
            y += add;
            g.drawLine(0, y, getWidth(), y);
        }
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

    public void addActionToSaveButton(ActionListener newActionListener) {
        exitButton.addActionListener(newActionListener);
    }
}
