package com.github.creme332.view.Settings;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.github.creme332.model.Setting;
import com.github.creme332.utils.SettingsManager;

public class Form extends JPanel implements ActionListener {
    public static String name = "settingsScreen";
    private JButton exitButton = new JButton("Exit");

    private Map<String, Setting> gameSettings = new SettingsManager().getSettings();

    public Form() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Settings");
        title.putClientProperty("FlatLaf.style", "font: $h0.font");
        titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        titlePanel.add(title);
        this.add(titlePanel);

        JPanel sectionsContainer = new JPanel();
        sectionsContainer.setLayout(new BoxLayout(sectionsContainer, BoxLayout.Y_AXIS));

        for (String settingName : gameSettings.keySet()) {
            Section section = new Section(gameSettings.get(settingName));
            sectionsContainer.add(section);
        }

        JScrollPane scrollPane = new JScrollPane(sectionsContainer);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        this.add(scrollPane);

        // add exit button
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        exitPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        exitButton.setPreferredSize(new Dimension(100, 60));
        exitButton.putClientProperty("FlatLaf.style", "font: bold 150% $defaultFont");
        exitPanel.add(exitButton);

        this.add(exitPanel);
    }

    // @Override
    // public void paint(Graphics g) {
    // super.paint(g);
    // int[][] dims = layout.getLayoutDimensions();
    // g.setColor(Color.white);
    // int x = 0;
    // for (int add : dims[0]) {
    // x += add;
    // g.drawLine(x, 0, x, getHeight());
    // }
    // int y = 0;
    // for (int add : dims[1]) {
    // y += add;
    // g.drawLine(0, y, getWidth(), y);
    // }
    // }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

    public void addActionToSaveButton(ActionListener newActionListener) {
        exitButton.addActionListener(newActionListener);
    }
}
