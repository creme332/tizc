import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

public class Mine {
    // Define colors for text highlighting
    Color GREEN_COLOR = new Color(204, 255, 153);
    Color RED_COLOR = new Color(255, 153, 153);

    // frame
    JFrame frame = new JFrame("tizc");
    int frameWidth = 600;
    int frameHeight = 600;

    // panels
    JPanel headerPanel = new JPanel();
    JPanel bodyPanel = new JPanel();

    // labels
    JLabel timerLabel = new JLabel();

    JTextArea typingArea = new JTextArea(10, 30); // text to be typed
    Border border = BorderFactory.createLineBorder(Color.red);

    // Define variables for text highlighting
    String text = "hello world how are you the man is so woman are your peace";
    Object lastIncorrectHighlight;
    int currentIndex = 0; // index of character to be typed

    // Define variables to track typing speed
    long startTime = -1;
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            long elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000;
            timerLabel.setText(String.format("%ds", elapsedSeconds));
        }
    };

    Mine() {
        // set frame properties
        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // set timer label properties
        timerLabel.setFont(new Font("Arial", Font.BOLD, 25));
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setText("00:00");
        timerLabel.setOpaque(true);
        timerLabel.setBackground(Color.gray);
        timerLabel.setForeground(Color.white);

        headerPanel.setLayout(new BorderLayout());
        headerPanel.setOpaque(true);
        // headerPanel.setBackground(Color.green);
        headerPanel.add(timerLabel);
        // headerPanel.setBorder(BorderFactory.createCompoundBorder(border,
        // BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        frame.add(headerPanel, BorderLayout.NORTH);

        typingArea.setText(text);
        typingArea.setFont(new Font("Monospaced", Font.PLAIN, 25));
        typingArea.setEditable(false);
        typingArea.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        bodyPanel.setLayout(new GridBagLayout());
        bodyPanel.setOpaque(true);
        bodyPanel.add(typingArea);
        frame.add(bodyPanel);

        // add event listener for keyboard presses
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {

                        // ignore all key events except key pressed event.
                        // ignore backspace key press (keycode = 8) as well
                        if (KeyEvent.KEY_PRESSED == e.getID() && e.getKeyCode() != 8) {
                            System.out.println(
                                    String.format("Current index = %d. Pressed %c", currentIndex, e.getKeyChar()));

                            // check if timer must be started
                            if (startTime < 0) {
                                // start timer
                                startTime = System.currentTimeMillis();
                                timer.schedule(task, 0, 1000);
                            }

                            if (e.getKeyChar() == text.charAt(currentIndex)) {
                                // correct character pressed
                                try {
                                    // remove any previous red highlight on current character
                                    if (lastIncorrectHighlight != null) {
                                        typingArea.getHighlighter().removeHighlight(lastIncorrectHighlight);
                                        lastIncorrectHighlight = null;
                                    }

                                    // color current character green
                                    highlightChar(currentIndex, GREEN_COLOR);

                                    currentIndex++;

                                    if (currentIndex == text.length()) {
                                        // stop timer
                                        timer.cancel();
                                        timer.purge();
                                    }

                                } catch (BadLocationException err) {
                                    // TODO Auto-generated catch block
                                    err.printStackTrace();
                                }
                            } else {
                                // incorrect character pressed

                                // color current character as red
                                try {
                                    lastIncorrectHighlight = highlightChar(currentIndex, RED_COLOR);

                                } catch (BadLocationException err) {
                                    // TODO Auto-generated catch block
                                    err.printStackTrace();
                                }
                            }
                        }
                        return false;
                    }
                });

        frame.setVisible(true);
    }

    public Object highlightChar(int index, Color color) throws BadLocationException {
        Highlighter highlighter = typingArea.getHighlighter();
        HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(color);
        return highlighter.addHighlight(index, index + 1, painter);
    }

}
