package com.github.creme332.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.creme332.view.HomeScreen;
import com.github.creme332.view.PlayScreen;

public class ControllerTest {
    Controller con;

    @Before
    public void setup() {
        con = new Controller();
    }

    @Test
    public void displayHomeScreenOnStartup() {
        assertTrue(con.frame.getScreen() == HomeScreen.name);
    }

    @Test
    public void displayPlayScreenOnGameRestart() {
        con.restartGame();
        assertTrue(con.frame.getScreen() == PlayScreen.name);

    }
}
