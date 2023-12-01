package com.github.creme332.view.Settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

import com.github.creme332.utils.SettingsManager;

public class Section extends JPanel implements ActionListener {
    private String name;
    private String unknownPreference = "Unknown";

    private SettingsManager settings = new SettingsManager();

    // TODO: Add javadoc
    public Section(String settingName, String[] options) {
        this.name = settingName;

        JLabel title = new JLabel(settingName);
        title.putClientProperty("FlatLaf.style", "font: bold $h2.regular.font");

        this.add(title);

        ButtonGroup group = new ButtonGroup();
        String currentPreference = settings.getData(settingName);

        // if no preference set, use first option as default preference
        if (currentPreference.equals(unknownPreference)) {
            currentPreference = options[0];
        }

        // create radio buttons for current setting
        for (int i = 0; i < options.length; i++) {
            JRadioButton btn = new JRadioButton(options[i]);
            btn.setFont(UIManager.getFont("large.font"));
            if (currentPreference.equals(options[i]))
                btn.setSelected(true);
            group.add(btn);
            this.add(btn);
            btn.addActionListener(this); // Register a listener for the radio buttons.

        }

        this.setOpaque(false);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(String.format("%1$s setting updated to %2$s", name, e.getActionCommand()));
        // System.out.println(this.getClass().getName());

        System.out.println(String.format("Previous %1$s setting = %2$s ",
                name, settings.getData(name)));
        settings.setData(name, e.getActionCommand());
    }
}
