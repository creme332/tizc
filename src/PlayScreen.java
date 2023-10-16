import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

public class PlayScreen extends JPanel {
    // panels
    JPanel headerPanel = new JPanel();
    JPanel bodyPanel = new JPanel();

    // labels
    JLabel timerLabel = new JLabel();

    JTextArea typingArea = new JTextArea(10, 30); // text to be typed
    Border border = BorderFactory.createLineBorder(Color.red);

    PlayScreen(Font myFont) {
        // set timer label properties
        timerLabel.setFont(myFont.deriveFont(25f));
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setText("Start typing when ready");
        timerLabel.setOpaque(true);

        headerPanel.setLayout(new BorderLayout());
        headerPanel.setOpaque(true);
        headerPanel.add(timerLabel);
        this.add(headerPanel, BorderLayout.NORTH);

        typingArea.setFont(myFont.deriveFont(30f));
        typingArea.setEditable(false);
        typingArea.setLineWrap(true);
        typingArea.setWrapStyleWord(true);
        typingArea.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        bodyPanel.setOpaque(true);
        bodyPanel.add(typingArea);
        this.add(bodyPanel);
    }

    public void showTime(long t) {
        timerLabel.setText(String.format("%ds", t));
    }

    public void showText(String t) {
        typingArea.setText(t);
    }

    public Object highlightChar(int index, Color color) throws BadLocationException {
        Highlighter highlighter = typingArea.getHighlighter();
        HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(color);
        return highlighter.addHighlight(index, index + 1, painter);
    }

    public void removeAllHighlights() {
        typingArea.getHighlighter().removeAllHighlights();
    }

    public void removeHighlight(Object highlightObj) {
        typingArea.getHighlighter().removeHighlight(highlightObj);
    }

}
