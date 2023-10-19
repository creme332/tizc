import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {
    // frame properties
    int frameWidth = 1000;
    int frameHeight = 800;

    // screens
    JPanel screenContainer = new JPanel();
    public HomeScreen homeScreen;
    public PlayScreen playScreen;
    public GameOverScreen gameOverScreen;
    CardLayout cl = new CardLayout();
    String currentScreen; // screen which is currently displayed

    Frame() {
        this.setTitle("tizc");

        // instantiate screens
        homeScreen = new HomeScreen();
        playScreen = new PlayScreen();
        gameOverScreen = new GameOverScreen();

        // set frame properties
        this.setSize(frameWidth, frameHeight);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add screens to screen container
        screenContainer.setLayout(cl);
        screenContainer.add(homeScreen, "homeScreen");
        screenContainer.add(playScreen, "playScreen");
        screenContainer.add(gameOverScreen, "gameOverScreen");
        setScreen("homeScreen");

        this.add(screenContainer);
    }

    public String getScreen() {
        return currentScreen;
    }

    public void showFrame() {
        this.setVisible(true);
    }

    public void setScreen(String newWindow) {
        if (newWindow != "homeScreen" && newWindow != "gameOverScreen" && newWindow != "playScreen") {
            System.out.println("Invalid screen name");
            return;
        }
        currentScreen = newWindow;
        cl.show(screenContainer, currentScreen);
    }
}
