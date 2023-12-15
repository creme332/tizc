package com.github.creme332.view.Settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.github.creme332.model.Setting;

public class Section extends JPanel implements ActionListener {
    private String name;
    private String unknownPreference = "Unknown";
    private Setting setting;
    Border blackline = BorderFactory.createLineBorder(Color.white);

    public Section(Setting setting) {
        this.setting = setting;
        this.name = setting.getKey();
        String[] options = setting.getOptions();

        // position children of JPanel vertically
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // create children of Section
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JPanel bodyPanel = new JPanel(new GridLayout(1, 2));

        // create children of bodyPanel
        JPanel descriptionPanel = new JPanel();
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel buttonsGroupPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        buttonsGroupPanel.setPreferredSize(new Dimension(500, 60));

        // buttonsPanel.setBorder(blackline);
        // buttonsGroupPanel.setBorder(blackline);

        // create title
        JLabel title = new JLabel(name);
        title.setIcon(setting.getIcon());
        title.putClientProperty("FlatLaf.style", "font: bold $h2.regular.font");
        titlePanel.add(title);
        this.add(titlePanel);

        // create description
        JTextArea descriptionLabel = new JTextArea(3, 40);
        descriptionLabel.setText(setting.getDescription());
        descriptionLabel.setEditable(false);
        descriptionLabel.setLineWrap(true);
        descriptionLabel.setWrapStyleWord(true);
        descriptionLabel.setOpaque(false); // Make transparent

        descriptionPanel.add(descriptionLabel);
        bodyPanel.add(descriptionPanel);

        ButtonGroup group = new ButtonGroup();
        String currentPreference = setting.getData();

        // if no preference set, use first option as default preference
        if (currentPreference.equals(unknownPreference)) {
            currentPreference = options[0];
        }

        // set global gbc constraints
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridy = 0; // align all items horizontally
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.right = 10; // add right margin of 10px to each item

        // create radio buttons for current setting
        for (int i = 0; i < options.length; i++) {
            JRadioButton btn = new JRadioButton(options[i]);

            // style button
            btn.setFont(UIManager.getFont("large.font"));
            btn.setBorderPainted(true);
            btn.setOpaque(true);

            if (currentPreference.equals(options[i]))
                btn.setSelected(true);
            group.add(btn);

            gbc.gridx = i;

            // for last option, do not set right margin
            if (i == options.length - 1)
                gbc.insets.right = 0;

            buttonsGroupPanel.add(btn, gbc);
            btn.addActionListener(this); // Register a listener for the radio buttons.

        }
        buttonsPanel.add(buttonsGroupPanel);
        bodyPanel.add(buttonsPanel);
        this.add(bodyPanel);

        this.setOpaque(false); // make section transparent
    }

    public void actionPerformed(ActionEvent e) {
        setting.setData(e.getActionCommand());
    }
}
