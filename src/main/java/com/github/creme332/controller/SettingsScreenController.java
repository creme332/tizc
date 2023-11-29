package com.github.creme332.controller;

import java.awt.event.ActionListener;

import com.github.creme332.view.Settings.Form;

public class SettingsScreenController {
    private Form screen = new Form();

    public SettingsScreenController() {

    }

    public Form getSettingsScreen() {
        return screen;
    }

    public void addExitButtonListener(ActionListener action) {
        screen.addActionToSaveButton(action);
    }

}
