package com.github.creme332.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

import com.github.creme332.view.PlayScreen;
import com.github.creme332.model.Model;
import com.github.creme332.utils.Calculator;

/**
 * Controller for PlayScreen
 */
public class PlayScreenController {
    Model model;
    PlayScreen playScreen = new PlayScreen();

    // setup variables for timer
    private Timer timer;
    private TimerTask task;

    private PropertyChangeSupport support; // variable for observer pattern

    public PlayScreenController(Model model) {
        // initialise model
        this.model = model;

        // PlayScreenController is a subject and notifies observers.
        support = new PropertyChangeSupport(this);

        initialiseGame();

        createKeyBindings();
    }

    /**
     * When game is over, notify listeners
     * 
     * @param listener
     */
    public void addGameOverListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener("gameOver", listener);
    }

    /**
     * Handles key event.
     * 
     * @param keyCommand key pressed
     */
    private void dispatchKeyEvent(String keyCommand) {
        // ignore keys which are not lowercase alphabets
        if (keyCommand.length() > 1)
            return;

        // ignore keypresses after game is over
        if (model.getCursorPos() >= model.getTypeText().length())
            return;

        // System.out.println(
        // String.format("Current index = %d. Pressed %s", model.getCursorPos(),
        // keyCommand));

        // when a key is pressed for the first time, start timer
        if (model.getStartTime() <= 0) {
            startTimer();
        }

        char charToType = ' '; // character that user must type

        try {
            charToType = model.getCurrentChar();
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        if (keyCommand.charAt(0) == charToType) {
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
            return;
        }

        // incorrect character pressed
        model.incrementMistakes();

        // highlight incorrectly typed character red, if it is not already red
        try {
            if (model.getBadHighlight() == null) {
                model.setBadHighlight(playScreen.highlightChar(model.getCursorPos(), false));
            }
        } catch (BadLocationException err) {
            err.printStackTrace();
        }

    }

    /**
     * Game over handler. Call this function when game is over.
     */
    public void handleGameOver() {
        stopTimer();

        // notify listeners that game is over
        support.firePropertyChange("gameOver", false, true);
    }

    /**
     * Starts timer on PlayScreen.
     */
    private void startTimer() {
        model.initStartTime();
        timer.schedule(task, 0, 1000);
    }

    /**
     * Stops timer on PlayScreen.
     */
    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
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
        String KEY_PRESS = "keypress"; // ! KEY_PRESS should not contain capital letters
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
                // when tab key is pressed while on playScreen, restart game
                initialiseGame();
            }
        };

        String TAB_PRESS = "tabpress"; // ! TAB_PRESS should not contain capital letters
        playScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), TAB_PRESS);
        playScreen.getActionMap().put(TAB_PRESS, tabAction);
    }

    /**
     * Returns PlayScreen, the screen where game takes place.
     * 
     * @return PlayScreen
     */
    public PlayScreen getPlayScreen() {
        return playScreen;
    }

    /**
     * Initialises all variables for a new game.
     */
    public void initialiseGame() {
        // stop timer
        stopTimer();

        // reset model
        model.reset();

        // create a new timer
        timer = new Timer();

        // create a timer task to calculate time elapsed and update timer on screen
        task = new TimerTask() {
            @Override
            public void run() {
                long ms = System.currentTimeMillis();
                long elapsedSeconds = (ms - model.getStartTime()) / 1000;
                model.setGameDuration(elapsedSeconds);
                playScreen.showTime(model.getGameDuration());

                // record current wpm
                double currentWPM = new Calculator()
                        .getWPM(
                                model.getCursorPos(),
                                elapsedSeconds);
                if (elapsedSeconds > 0) {
                    try {
                        model.recordWPM(elapsedSeconds, currentWPM);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        };

        // reset game duration
        playScreen.showTime(0);

        // reset highlights
        playScreen.removeAllHighlights();

        // show text to be typed
        playScreen.showText(model.getTypeText());
    }
}
