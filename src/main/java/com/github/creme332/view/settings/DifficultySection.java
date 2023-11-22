package com.github.creme332.view.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

public class DifficultySection extends JPanel implements ActionListener {

    public DifficultySection(String text, String[] options) {
        JLabel title = new JLabel();
        title.setText(text);
        // TODO: Make title bold
        title.setFont(UIManager.getFont("large.font"));

        this.add(title);

        ButtonGroup group = new ButtonGroup();

        for (int i = 0; i < options.length; i++) {
            JRadioButton btn = new JRadioButton(options[i]);
            group.add(btn);
            this.add(btn);
            btn.addActionListener(this); // Register a listener for the radio buttons.

        }

        // this.setPreferredSize(new Dimension(400, 50));
        this.setOpaque(false);

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

}
