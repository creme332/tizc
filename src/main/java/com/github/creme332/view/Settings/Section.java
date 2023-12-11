package com.github.creme332.view.Settings;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import com.github.creme332.model.Setting;
import com.github.creme332.utils.SettingsManager;

public class Section extends JPanel implements ActionListener {
    private String name;
    private String unknownPreference = "Unknown";
    private SettingsManager settings = new SettingsManager();

    public Section(Setting setting) {
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

        // create title
        JLabel title = new JLabel(name);
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
        String currentPreference = settings.getData(name);

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
            buttonsPanel.add(btn);
            btn.addActionListener(this); // Register a listener for the radio buttons.

        }

        bodyPanel.add(buttonsPanel);
        this.add(bodyPanel);

        this.setOpaque(false); // make section transparent
    }

    public void actionPerformed(ActionEvent e) {
        // debugging stuffs:
        // System.out.println(String.format("%1$s setting updated to %2$s", name,
        // e.getActionCommand()));
        // System.out.println(this.getClass().getName());
        // System.out.println(String.format("Previous %1$s setting = %2$s ",
        // name, settings.getData(name)));
        settings.setData(name, e.getActionCommand());
    }
}
