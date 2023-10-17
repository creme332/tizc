import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.text.BadLocationException;

public class Mine {
    // declare frame and screens that it will display
    Frame frame = new Frame();
    HomeScreen homeScreen = frame.homeScreen;
    PlayScreen playScreen = frame.playScreen;
    GameOverScreen gameOverScreen = frame.gameOverScreen;

    // generate some text to type
    String typeText;

    // Define variables for text highlighting
    Object lastIncorrectHighlight;
    int charPtr = 0; // index of character to be typed

    // Define variables to track typing speed
    long startTime = -1;
    long gameDuration = 0;
    Timer timer;

    // create a task for timer
    TimerTask task;

    Mine() {
        // listen to start button presses on home screen
        homeScreen.startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // when start button is clicked, show playScreen
                frame.setScreen("playScreen");
            }
        });

        gameOverScreen.restarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                initialise();
                // go to play screen
                frame.setScreen("playScreen");
            }
        });
        initialise();
        createKeyBindings();
        frame.showFrame();
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
                playScreen.highlightChar(charPtr, frame.GREEN_COLOR);

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
                    lastIncorrectHighlight = playScreen.highlightChar(charPtr, frame.RED_COLOR);
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

    private void initialise() {
        timer = new Timer();

        // create a timer task
        task = new TimerTask() {
            @Override
            public void run() {
                long ms = System.currentTimeMillis();
                long elapsedSeconds = (ms - startTime) / 1000;
                gameDuration = elapsedSeconds;
                playScreen.showTime(gameDuration);
            }
        };

        // reset game duration
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
        frame.setScreen("gameOverScreen");
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
