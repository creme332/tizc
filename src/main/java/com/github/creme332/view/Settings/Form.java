package com.github.creme332.view.Settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.SpinnerListModel;
import javax.swing.UIManager;

public class Form extends JPanel implements ActionListener {
    public static String name = "settingsScreen";
    private JButton saveButton = new JButton("Save & Exit");

    Integer[] monthStrings = { 10, 30, 60, 120, 500 }; // get month names
    SpinnerListModel monthModel = new SpinnerListModel(monthStrings);

    Section modeSection = new Section("Mode", new String[] { "word", "quote" });
    Section difficultySection = new Section("Difficulty",
            new String[] { "easy", "medium", "hard" });
    Section speedSection = new Section("Live speed", new String[] { "hide", "show" });
    Section accuracySection = new Section("Live accuracy", new String[] { "hide", "show" });
    Section timerSection = new Section("Live timer", new String[] { "hide", "show" });

    GridBagLayout layout;

    public Form() {

        GridBagConstraints gbc = new GridBagConstraints();
        layout = new GridBagLayout();
        this.setLayout(layout);

        // set global gbc constraints
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.add(modeSection, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.add(difficultySection, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        this.add(speedSection, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        this.add(accuracySection, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        this.add(timerSection, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        saveButton.setPreferredSize(new Dimension(100, 100));
        saveButton.setFont(UIManager.getFont("h1.font"));

        this.add(saveButton, gbc);
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
        saveButton.addActionListener(newActionListener);
    }
}
