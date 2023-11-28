package com.github.creme332.controller;

import java.util.function.Consumer;

import com.github.creme332.view.Settings.Form;

public class SettingsScreenController {
    private Form screen = new Form();

    public SettingsScreenController() {

    }

    public Form getSettingsScreen() {
        return screen;
    }

    /**
     * Switch to home screen.
     * 
     * @param i
     * @param aMethod
     */
    public void dansMethod(String i, Consumer<String> aMethod) {
        /*
         * you can now call the passed method by saying aMethod.accept(i), and it
         * will be the equivalent of saying A.methodToPass(i)
         */
        aMethod.accept(i);
    }

    // TODO: When save button is clicked,
    // 1. save settings to preferences
    // 2. switch to home screen

}
