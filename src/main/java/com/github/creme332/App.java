package com.github.creme332;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.github.creme332.controller.Controller;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        new Controller();
    }
}
