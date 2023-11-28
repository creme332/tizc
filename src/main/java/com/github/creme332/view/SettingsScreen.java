package com.github.creme332.view;

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
import com.github.creme332.view.settings.DifficultySection;

public class SettingsScreen extends JPanel implements ActionListener {
    public static String name = "settingsScreen";
    private JButton saveButton = new JButton("Save & Exit");

    Integer[] monthStrings = { 10, 30, 60, 120, 500 }; // get month names
    SpinnerListModel monthModel = new SpinnerListModel(monthStrings);

    DifficultySection modeSection = new DifficultySection("Mode", new String[] { "word", "quote" }, "word");
    DifficultySection abs = new DifficultySection("Difficulty", new String[] { "easy", "medium", "hard" }, "easy");
    DifficultySection bbb = new DifficultySection("Live speed", new String[] { "hide", "show" }, "hide");
    DifficultySection ccc = new DifficultySection("Live accuracy", new String[] { "hide", "show" }, "hide");
    DifficultySection ddd = new DifficultySection("Live timer", new String[] { "hide", "show" }, "show");

    GridBagLayout layout;

    public SettingsScreen() {

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
        this.add(abs, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        this.add(bbb, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        this.add(ccc, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        this.add(ddd, gbc);

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

    // @Override
    // protected void paintComponent(Graphics g) {
    // // add background image
    // super.paintComponent(g);
    // ImageIcon img = new ImageIcon(this.getClass().getResource("/bg1.jpg"));

    // g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    // }

}
