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

    Model model = new Model();
    Timer timer = new Timer();
    TimerTask task; // create a task for timer

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
        if (model.charPtr >= model.typeText.length())
            return;

        System.out.println(
                String.format("Current index = %d. Pressed %s", model.charPtr, keyCommand));

        // when a key is pressed for the first time, start timer
        if (model.startTime < 0) {
            startTimer();
        }

        if (keyCommand.charAt(0) == model.typeText.charAt(model.charPtr)) {
            // correct character pressed
            try {
                // remove any previous red highlight on current character
                if (model.lastIncorrectHighlight != null) {
                    playScreen.removeHighlight(model.lastIncorrectHighlight);
                    model.lastIncorrectHighlight = null;
                }

                // color current character green
                playScreen.highlightChar(model.charPtr, true);

                // point to next character
                model.charPtr++;

                // check if all words have been typed => game over
                if (model.charPtr == model.typeText.length()) {
                    handleGameOver();
                }

            } catch (BadLocationException err) {
                err.printStackTrace();
            }
        } else {
            // incorrect character pressed
            model.totalMistakes++;

            // highlight incorrectly typed character red, if it is not already red
            try {
                if (model.lastIncorrectHighlight == null) {
                    model.lastIncorrectHighlight = playScreen.highlightChar(model.charPtr, false);
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
        model = new Model();
        timer = new Timer();

        // create a timer task to calculate time elapsed and update timer on screen
        task = new TimerTask() {
            @Override
            public void run() {
                long ms = System.currentTimeMillis();
                long elapsedSeconds = (ms - model.startTime) / 1000;
                model.gameDuration = elapsedSeconds;
                playScreen.showTime(model.gameDuration);
            }
        };

        // reset game duration
        playScreen.showTime(0);

        // reset highlights
        playScreen.removeAllHighlights();

        // update typeText
        model.typeText = (new WordGenerator(5)).getString();

        playScreen.showText(model.typeText);
    }

    private void handleGameOver() {
        // stop timer
        stopTimer();

        long textLength = model.typeText.length();
        gameOverScreen.setTimeTaken(model.gameDuration);
        gameOverScreen.setWPM(60 * textLength / (5 * model.gameDuration));
        gameOverScreen.setAccuracy(100 * (textLength - model.totalMistakes) / textLength);
        // show game over screen
        frame.setScreen("gameOverScreen");
    }

    private void startTimer() {
        model.startTime = System.currentTimeMillis();
        timer.schedule(task, 0, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

}
