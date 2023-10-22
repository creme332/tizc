package controller;

import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.text.BadLocationException;

import model.Model;
import view.*;

/**
 * Controls all the logic in the application by linking views and model.
 */
public class Controller {
    // declare frame and screens that it will display
    private Frame frame = new Frame();
    private HomeScreen homeScreen = frame.homeScreen;
    private PlayScreen playScreen = frame.playScreen;
    private GameOverScreen gameOverScreen = frame.gameOverScreen;

    private Model model; // game state

    // setup variables for timer found in playScreen
    private Timer timer;
    private TimerTask task;

    public Controller() {
        // listen to start button presses on home screen
        homeScreen.startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // when start button is clicked, show playScreen
                frame.setScreen("playScreen");
            }
        });

        // listen to restart button presses on game over screen
        gameOverScreen.restarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // when restart button is clicked, reset variables and move to play screen
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
        if (model.getCursorPos() >= model.getTypeText().length())
            return;

        System.out.println(
                String.format("Current index = %d. Pressed %s", model.getCursorPos(), keyCommand));

        // when a key is pressed for the first time, start timer
        if (model.getStartTime() <= 0) {
            startTimer();
        }

        if (keyCommand.charAt(0) == model.getCurrentChar()) {
            // correct character pressed
            try {
                // remove any previous red highlight on current character
                if (model.getBadHighlight() != null) {
                    playScreen.removeHighlight(model.getBadHighlight());
                    model.setBadHighlight(null);
                }

                // color current character green
                playScreen.highlightChar(model.getCursorPos(), true);

                // point to next character
                model.incrementCursor();

                // check if all words have been typed => game over
                if (model.getCursorPos() == model.getTypeText().length()) {
                    handleGameOver();
                }

            } catch (BadLocationException err) {
                err.printStackTrace();
            }
        } else {
            // incorrect character pressed
            model.incrementMistakes();
            ;

            // highlight incorrectly typed character red, if it is not already red
            try {
                if (model.getBadHighlight() == null) {
                    model.setBadHighlight(playScreen.highlightChar(model.getCursorPos(), false));
                }
            } catch (BadLocationException err) {
                err.printStackTrace();
            }
        }
    }

    /**
     * Create keybindings for play screen.
     * 
     * @see <a href=
     *      "https://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html">https://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html</a>
     * @see <a href= "https://www.asciitable.com/">https://www.asciitable.com/</a>
     * 
     * 
     */
    private void createKeyBindings() {
        Action letterPressAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // when a letter is pressed, highlight char pressed and move cursor if needed
                dispatchKeyEvent(e.getActionCommand());
            }
        };

        // add keybindings for all letters
        String KEY_PRESS = "keypress"; // ! TAB_PRESS should not contain capital letters
        // Note: ascii codes for A-Z = 65-90.
        for (int keycode = 65; keycode < 91; keycode++) {
            playScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                    .put(KeyStroke.getKeyStroke(keycode, 0), KEY_PRESS);
            playScreen.getActionMap().put(KEY_PRESS, letterPressAction);
        }

        // add keybinding for space bar
        playScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), KEY_PRESS);
        playScreen.getActionMap().put(KEY_PRESS, letterPressAction);

        // create keybinding for tab key
        Action tabAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Tab key pressed");
                // when tab key is pressed while on playScreen, restart game
                initialise();
            }
        };

        String TAB_PRESS = "tabpress"; // ! TAB_PRESS should not contain capital letters
        playScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), TAB_PRESS);
        playScreen.getActionMap().put(TAB_PRESS, tabAction);
    }

    /**
     * Initialises variables for a new game
     */
    private void initialise() {
        model = new Model();

        // reset game duration
        playScreen.showTime(0);

        // reset highlights in text area
        playScreen.removeAllHighlights();

        // display text on playScreen
        playScreen.showText(model.getTypeText());

        timer = new Timer();

        // create a timer task to calculate time elapsed and update timer on screen
        task = new TimerTask() {
            @Override
            public void run() {
                long ms = System.currentTimeMillis();
                long elapsedSeconds = (ms - model.getStartTime()) / 1000;
                model.setGameDuration(elapsedSeconds);
                playScreen.showTime(elapsedSeconds);
            }
        };
    }

    /**
     * Calculates the typing speed in WPM (words per minute) of a player
     * 
     * @param charCount total number of characters (including spaces) in text typed
     * @param timeTaken time taken in seconds by player
     * @return WPM
     */
    private long getWPM(long charCount, long timeTaken) {
        long secondsInMinute = 60;
        long charPerWord = 5; // average number of characters in a word
        if (timeTaken > 0) {
            return (secondsInMinute * charCount / (charPerWord * timeTaken));
        }
        return 0;
    }

    /**
     * Calculates the accuracy of a player
     * 
     * @param charCount    total number of characters (including spaces) in text
     *                     typed
     * @param mistakeCount number of characters wrongly typed
     * @return A percentage (0-100)
     */
    private long getAccuracy(long charCount, long mistakeCount) {
        if (charCount > 0)
            return 100 * (charCount - mistakeCount) / charCount;
        return 0;

    }

    private void handleGameOver() {
        // stop timer
        stopTimer();

        long textLength = model.getTypeText().length(); // total characters in text to be typed
        long gameDuration = model.getGameDuration();

        gameOverScreen.setTimeTaken(model.getGameDuration()); // display game duration
        gameOverScreen.setWPM(getWPM(textLength, gameDuration)); // display wpm
        gameOverScreen.setAccuracy(getAccuracy(textLength, model.getTotalMistakes())); // display accuracy

        // show game over screen
        frame.setScreen("gameOverScreen");
    }

    private void startTimer() {
        model.initStartTime();
        timer.schedule(task, 0, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

}
