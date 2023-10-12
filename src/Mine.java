import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

public class Mine {
    Color GREEN_COLOR = new Color(204, 255, 153);
    Color RED_COLOR = new Color(255, 153, 153);
    int tileSize = 70;
    int numRows = 9;
    int numCols = 9;
    int boardWidth = numCols * tileSize;
    int boardHeight = numRows * tileSize;
    int currentIndex = 0;

    JFrame frame = new JFrame("tizc");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    String text = "hello world how are you";
    JTextArea textArea = new JTextArea(10, 30);
    Object lastIncorrectHighlight;

    // Timer stuffs
    long startTime = -1;
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            long elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000;
            textLabel.setText(String.format("%ds", elapsedSeconds));
        }
    };

    Mine() {
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.BOLD, 25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("00:00");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        textArea.setText(text);
        textArea.setFont(textArea.getFont().deriveFont(25f)); // will only change size to 12pt
        textArea.setEditable(false);

        frame.add(textArea);

        // JOptionPane.showMessageDialog(null, new JScrollPane(textArea));

        boardPanel.setLayout(new GridLayout(numRows, numCols));
        boardPanel.setBackground(Color.white);
        // frame.add(boardPanel);

        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {

                        // ignore all key events except key pressed event.
                        // ignore backspace key press (keycode = 8) as well
                        if (KeyEvent.KEY_PRESSED == e.getID() && e.getKeyCode() != 8) {
                            System.out.println(currentIndex);

                            // check if timer must be started
                            if (startTime < 0) {
                                // start timer
                                startTime = System.currentTimeMillis();
                                timer.schedule(task, 0, 1000);
                            }

                            // textLabel.setText(textLabel.getText() + e.getKeyChar());
                            if (e.getKeyChar() == text.charAt(currentIndex)) {
                                // correct character pressed
                                try {
                                    // remove any previous red highlight on current character
                                    if (lastIncorrectHighlight != null) {
                                        textArea.getHighlighter().removeHighlight(lastIncorrectHighlight);
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

    private String removeLastChar(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 1));
    }

    public Object highlightChar(int index, Color color) throws BadLocationException {
        Highlighter highlighter = textArea.getHighlighter();
        HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(color);
        return highlighter.addHighlight(index, index + 1, painter);
    }

}
