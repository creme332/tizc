import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

public class Mine {

    int tileSize = 70;
    int numRows = 9;
    int numCols = 9;
    int boardWidth = numCols * tileSize;
    int boardHeight = numRows * tileSize;

    JFrame frame = new JFrame("tizc");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JTextArea textArea = new JTextArea(10, 30);

    String text = "hello world. How are you?";

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
        try {
            highlight();
        } catch (BadLocationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // JOptionPane.showMessageDialog(null, new JScrollPane(textArea));

        boardPanel.setLayout(new GridLayout(numRows, numCols));
        boardPanel.setBackground(Color.white);
        // frame.add(boardPanel);

        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        if (KeyEvent.KEY_PRESSED == e.getID()) {
                            System.out.println(e.getKeyCode());
                            // check if backspace is pressed
                            if (e.getKeyCode() == 8) {
                                // remove last character entered
                                textLabel.setText(removeLastChar(textLabel.getText()));
                                System.out.println("Backspace pressed");
                            } else {
                                // add new character
                                textLabel.setText(textLabel.getText() + e.getKeyChar());

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

    public void highlight() throws BadLocationException {
        Highlighter highlighter = textArea.getHighlighter();
        HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.pink);
        int p0 = text.indexOf("world");
        int p1 = p0 + "world".length();
        highlighter.addHighlight(p0, p1, painter);
    }

}
