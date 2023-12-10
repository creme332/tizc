package com.github.creme332;

import com.github.creme332.controller.Controller;
import com.github.creme332.themes.TizcTheme;

public class App {
    public static void main(String[] args) {
        TizcTheme.setup();
        new Controller();
    }
}
