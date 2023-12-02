package com.github.creme332.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import com.github.creme332.utils.PoppinsFont;

/**
 * Main screen where user plays.
 */
public class PlayScreen extends JPanel {
    // Define colors for highlighting
    private Color GOOD_COLOR = Color.BLACK; // color of highlight when correct character is typed
    private Color BAD_COLOR = new Color(78, 78, 78); // color of highlight when incorrect character is typed

    private JLabel timerLabel; // label to display time
    private JLabel speedLabel; // label to display speed
    private JLabel accuracyLabel; // label to display accuracy

    private JTextArea typingArea = new JTextArea(10, 30); // text to be typed
    public static String name = "playScreen";

    public PlayScreen() {
        Font PoppinsLight = new PoppinsFont().Light;

        // set timer label properties
        timerLabel = createStyledLabel();
        showTime(0);

        // set accuracy label properties
        accuracyLabel = createStyledLabel();
        showAccuracy(0);

        // set speed label properties
        speedLabel = createStyledLabel();
        showSpeed(0);

        // panels for layout
        JPanel headerPanel = new JPanel(new FlowLayout()); // container for clock
        JPanel bodyPanel = new JPanel(); // container for typing area

        // add borders to panels for debugging
        // headerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        // bodyPanel.setBorder(BorderFactory.createLineBorder(Color.green));
        // this.setBorder(BorderFactory.createLineBorder(Color.red));

        // setup header panel
        headerPanel.setPreferredSize(new Dimension(400, 50));
        headerPanel.setOpaque(false); // Make panel transparent

        // add labels to headerPanel
        headerPanel.add(timerLabel);
        headerPanel.add(Box.createHorizontalStrut(50));
        headerPanel.add(speedLabel);
        headerPanel.add(Box.createHorizontalStrut(50));
        headerPanel.add(accuracyLabel);

        // setup typing area
        typingArea.setFont(PoppinsLight.deriveFont(30f));
        typingArea.setForeground(Color.white);
        typingArea.setEditable(false);
        typingArea.setLineWrap(true);
        typingArea.setWrapStyleWord(true);
        typingArea.setOpaque(false); // Make transparent
        bodyPanel.setPreferredSize(new Dimension(800, 500));
        bodyPanel.setOpaque(false);
        bodyPanel.add(typingArea);

        // setup frame layout
        this.setLayout(new BorderLayout());

        this.add(headerPanel, BorderLayout.PAGE_START);
        this.add(bodyPanel, BorderLayout.CENTER);
    }

    public void toggleLiveTimer(boolean isVisible) {
        timerLabel.setVisible(isVisible);
    }

    public void toggleLiveSpeed(boolean isVisible) {
        speedLabel.setVisible(isVisible);
    }

    public void toggleLiveAccuracy(boolean isVisible) {
        accuracyLabel.setVisible(isVisible);
    }

    private JLabel createStyledLabel() {
        Font PoppinsBold = new PoppinsFont().Bold;
        JLabel myLabel = new JLabel();

        myLabel.setFont(PoppinsBold.deriveFont(25f));
        myLabel.setHorizontalAlignment(JLabel.CENTER);
        myLabel.setOpaque(false);
        myLabel.setForeground(Color.WHITE);

        return myLabel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // add background image to screen
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bg2.jpg"));

        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }

    /**
     * Displays current time in seconds in timerLabel
     * 
     * @param timeInSeconds
     */
    public void showTime(long timeInSeconds) {
        timerLabel.setText(String.format("%ds", timeInSeconds));
    }

    /**
     * Displays current speed in wpm in speedLabel
     * 
     * @param wpm
     */
    public void showSpeed(long wpm) {
        speedLabel.setText(String.format("%d wpm", wpm));
    }

    /**
     * Displays current accuracy in speedLabel
     * 
     * @param accuracy
     */
    public void showAccuracy(long accuracy) {
        accuracyLabel.setText(String.format("%d %%", accuracy));
    }

    /**
     * Displays text to be typed in text area
     * 
     * @param t text to be typed.
     */
    public void showText(String t) {
        typingArea.setText(t);
    }

    /**
     * Highlights a particular character in text area.
     * 
     * @param index       index of character to be highlighted. First character has
     *                    index 0.
     * @param correctChar True if character was correctly typed. Determines
     *                    highlighter color.
     * @return highlighter object.
     * @throws BadLocationException
     */
    public Object highlightChar(int index, Boolean correctChar) throws BadLocationException {
        Highlighter highlighter = typingArea.getHighlighter();
        HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(correctChar ? GOOD_COLOR : BAD_COLOR);
        return highlighter.addHighlight(index, index + 1, painter);
    }

    /**
     * Removes all highlights from the text area.
     */
    public void removeAllHighlights() {
        typingArea.getHighlighter().removeAllHighlights();
    }

    /**
     * Removes the highlight at a particular position.
     * 
     * @param highlightObj highlighter object for the highlight at a particular
     *                     position.
     */
    public void removeHighlight(Object highlightObj) {
        typingArea.getHighlighter().removeHighlight(highlightObj);
    }

}
