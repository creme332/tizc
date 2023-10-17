import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class Frame extends JFrame {
    // Define colors and font
    Color GREEN_COLOR = new Color(204, 255, 153);
    Color RED_COLOR = new Color(255, 153, 153);
    Font PoppinsLightFont;

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

        // fetch font
        try {
            File font_file = new File("resources/font/Poppins/Poppins-Light.ttf");
            PoppinsLightFont = Font.createFont(Font.TRUETYPE_FONT, font_file);
        } catch (FontFormatException | IOException ex) {
            System.out.println(ex);
        }

        // instantiate screens
        homeScreen = new HomeScreen(PoppinsLightFont);
        playScreen = new PlayScreen(PoppinsLightFont);
        gameOverScreen = new GameOverScreen(PoppinsLightFont);

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
