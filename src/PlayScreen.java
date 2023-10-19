import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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

    PlayScreen() {
        Font PoppinsLight = new PoppinsFont().Light;
        Font PoppinsBold = new PoppinsFont().Bold;

        // set timer label properties
        timerLabel.setFont(PoppinsBold.deriveFont(25f));
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setText("Start typing when ready");
        timerLabel.setOpaque(false);
        timerLabel.setForeground(Color.WHITE);

        headerPanel.setLayout(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.add(timerLabel);
        this.add(headerPanel, BorderLayout.NORTH);

        typingArea.setFont(PoppinsLight.deriveFont(30f));
        typingArea.setForeground(Color.white);
        typingArea.setEditable(false);
        typingArea.setLineWrap(true);
        typingArea.setWrapStyleWord(true);
        typingArea.setOpaque(false);
        // typingArea.setBorder(BorderFactory.createCompoundBorder(border,
        // BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        bodyPanel.setOpaque(false);
        bodyPanel.add(typingArea);
        this.add(bodyPanel);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        ImageIcon img = new ImageIcon("resources/bg1.jpg");

        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
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
