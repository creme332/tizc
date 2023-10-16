import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.text.BadLocationException;

public class Mine {
    // Define colors and font
    Color GREEN_COLOR = new Color(204, 255, 153);
    Color RED_COLOR = new Color(255, 153, 153);
    Font PoppinsLightFont;

    // frame
    JFrame frame = new JFrame("tizc");
    int frameWidth = 1000;
    int frameHeight = 800;

    // screens
    JPanel screenContainer = new JPanel();
    HomeScreen homeScreen;
    PlayScreen playScreen;
    GameOverScreen gameOverScreen;
    CardLayout cl = new CardLayout();
    String currentScreen; // screen which is currently displayed

    // generate some text to type
    String typeText;

    // Define variables for text highlighting
    Object lastIncorrectHighlight;
    int charPtr = 0; // index of character to be typed

    // Define variables to track typing speed
    long startTime = -1;
    long gameDuration = 0;
    Timer timer = new Timer();

    // create a task for timer
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            long ms = System.currentTimeMillis();
            long elapsedSeconds = (ms - startTime) / 1000;
            gameDuration = elapsedSeconds;
            playScreen.showTime(gameDuration);
        }
    };

    Mine() {
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
        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add screens to screen container
        screenContainer.setLayout(cl);
        screenContainer.add(homeScreen, "homeScreen");
        screenContainer.add(playScreen, "playScreen");
        screenContainer.add(gameOverScreen, "gameOverScreen");
        setScreen("homeScreen");

        // listen to start button presses on home screen
        homeScreen.startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // when start button is clicked, show playScreen
                setScreen("playScreen");
            }
        });

        gameOverScreen.restarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                initialise();
                // go to play screen
                setScreen("playScreen");
            }
        });
        initialise();
        createKeyBindings();
        frame.add(screenContainer);
        frame.setVisible(true);
    }

    public void dispatchKeyEvent(String keyCommand) {
        // ignore keys which are not lowercase alphabets
        if (keyCommand.length() > 1)
            return;

        // ignore keypresses after game is over
        if (charPtr >= typeText.length())
            return;

        System.out.println(
                String.format("Current index = %d. Pressed %s", charPtr, keyCommand));

        // when a key is pressed for the first time, start timer
        if (startTime < 0) {
            startTimer();
        }

        if (keyCommand.charAt(0) == typeText.charAt(charPtr)) {
            // correct character pressed
            try {
                // remove any previous red highlight on current character
                if (lastIncorrectHighlight != null) {
                    playScreen.removeHighlight(lastIncorrectHighlight);
                    lastIncorrectHighlight = null;
                }

                // color current character green
                playScreen.highlightChar(charPtr, GREEN_COLOR);

                // point to next character
                charPtr++;

                // check if all words have been typed => game over
                if (charPtr == typeText.length()) {
                    handleGameOver();
                }

            } catch (BadLocationException err) {
                err.printStackTrace();
            }
        } else {
            // incorrect character pressed

            // highlight incorrectly typed character red, if it is not already red
            try {
                if (lastIncorrectHighlight == null) {
                    lastIncorrectHighlight = playScreen.highlightChar(charPtr, RED_COLOR);
                }
            } catch (BadLocationException err) {
                err.printStackTrace();
            }
        }
    }

    /**
     * Creates keybindings for play screen.
     */
    private void createKeyBindings() {
        Action keyPressAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispatchKeyEvent(e.getActionCommand());
            }
        };

        String KEY_PRESS = "keypress";
        for (int keycode = 1; keycode < 91; keycode++) {
            playScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                    .put(KeyStroke.getKeyStroke(keycode, 0), KEY_PRESS);
            playScreen.getActionMap().put(KEY_PRESS, keyPressAction);
        }
    }

    private void setScreen(String newWindow) {
        if (newWindow != "homeScreen" && newWindow != "gameOverScreen" && newWindow != "playScreen") {
            System.out.println("Invalid screen name");
            return;
        }
        currentScreen = newWindow;
        cl.show(screenContainer, currentScreen);
    }

    private void initialise() {
        // reset game duration
        gameDuration = -1;
        playScreen.showTime(0);

        // reset start time
        startTime = -1;

        // reset highlights
        playScreen.removeAllHighlights();

        // reset charPtr
        charPtr = 0;

        // update typeText
        typeText = (new WordGenerator(5)).getString();

        playScreen.showText(typeText);
    }

    private void handleGameOver() {
        // stop timer
        stopTimer();

        gameOverScreen.setTimeTaken(gameDuration);
        gameOverScreen.setWPM(60 * typeText.length() / (5 * gameDuration));

        // show game over screen
        setScreen("gameOverScreen");
    }

    private void startTimer() {
        startTime = System.currentTimeMillis();
        timer.schedule(task, 0, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

}
