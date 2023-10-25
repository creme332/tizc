package controller;

import java.awt.event.ActionListener;

import view.HomeScreen;

public class HomeScreenController {
    private HomeScreen homeScreen = new HomeScreen();

    public void addStartButtonListener(ActionListener newActionListener) {
        homeScreen.addStartButtonListener(newActionListener);
    }

    public HomeScreen getHomeScreen() {
        return homeScreen;
    }
}
