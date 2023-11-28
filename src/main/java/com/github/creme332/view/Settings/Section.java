package com.github.creme332.view.Settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

public class Section extends JPanel implements ActionListener {
    private Preferences preferences;
    private String name;
    private String unknownPreference = "Unknown";

    // TODO: Add javadoc
    public Section(String settingName, String[] options) {
        this.name = settingName;

        // TODO: Ensure that options have length at least 1

        JLabel title = new JLabel(settingName);
        title.putClientProperty("FlatLaf.style", "font: bold $h2.regular.font");

        this.add(title);

        ButtonGroup group = new ButtonGroup();
        String currentPreference = getCurrentOption();

        // if no preference set, use first option as default preference
        if (currentPreference == unknownPreference) {
            currentPreference = options[0];
        }

        // create radio buttons for current setting
        for (int i = 0; i < options.length; i++) {
            JRadioButton btn = new JRadioButton(options[i]);
            btn.setFont(UIManager.getFont("large.font"));
            if (currentPreference == options[i])
                btn.setSelected(true);
            group.add(btn);
            this.add(btn);
            btn.addActionListener(this); // Register a listener for the radio buttons.

        }

        this.setOpaque(false);
    }

    public String getCurrentOption() {
        preferences = Preferences.userRoot().node(this.getClass().getName());
        return preferences.get(name, unknownPreference);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        System.out.println(this.getClass().getName());

        System.out.println(String.format("Previous %1$s setting = %2$s ",
                name, getCurrentOption()));

        preferences.put(name, e.getActionCommand());
    }
}
