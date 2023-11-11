package com.github.creme332.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import com.github.creme332.utils.PoppinsFont;

/**
 * Screen displayed when game ends.
 */
public class GameOverScreen extends JPanel {
    private JButton restarButton = new JButton("Restart");
    private JLabel timeTakenText = new JLabel();
    private JLabel wpmText = new JLabel();
    private JLabel cpmText = new JLabel();

    private JLabel accuracyText = new JLabel();
    public static String name = "gameOverScreen";

    WPMChart chart = new WPMChart();
    JPanel chartPanel;
    GridBagLayout layout;

    public GameOverScreen() {
        PoppinsFont myFont = new PoppinsFont();
        float mediumFontSize = 50; // font size to be used in JLabels
        int iconSize = 50;

        GridBagConstraints gbc = new GridBagConstraints();
        layout = new GridBagLayout();
        this.setLayout(layout);

        ImageIcon timerIcon = loadIcon("/icon/deadline.png", iconSize);
        ImageIcon speedometerIcon = loadIcon("/icon/speedometer.png", iconSize);
        ImageIcon accuracyIcon = loadIcon("/icon/accuracy.png", iconSize);

        // styles for time taken
        setTimeTaken(0);
        timeTakenText.setFont(myFont.Regular.deriveFont(mediumFontSize));
        timeTakenText.setIcon(timerIcon);
        timeTakenText.setHorizontalAlignment(JLabel.CENTER);
        timeTakenText.setForeground(Color.WHITE);

        // styles for speed
        setWPM(0);
        wpmText.setFont(myFont.Regular.deriveFont(mediumFontSize));
        wpmText.setIcon(speedometerIcon);
        wpmText.setHorizontalAlignment(JLabel.CENTER);
        wpmText.setForeground(Color.WHITE);

        // styles for CPM
        setCPM(0);
        cpmText.setFont(myFont.Regular.deriveFont(mediumFontSize));
        cpmText.setIcon(speedometerIcon);
        cpmText.setHorizontalAlignment(JLabel.CENTER);
        cpmText.setForeground(Color.WHITE);

        // styles for accuracy
        setAccuracy(0);
        accuracyText.setFont(myFont.Regular.deriveFont(mediumFontSize));
        accuracyText.setIcon(accuracyIcon);
        accuracyText.setHorizontalAlignment(JLabel.CENTER);
        accuracyText.setForeground(Color.WHITE);

        // styles for restartButton
        restarButton.setFocusPainted(false);
        restarButton.setFont(myFont.Regular.deriveFont(30f));
        restarButton.setContentAreaFilled(false);
        restarButton.setForeground(Color.WHITE);

        // styles for chart
        chartPanel = new XChartPanel<XYChart>(chart.get());
        chartPanel.setOpaque(false);

        // set global gbc constraints
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // position chart
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        this.add(chartPanel, gbc);

        // position timeTakenText at (1, 0) with unit width
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(timeTakenText, gbc);

        // position wpmText at (1, 1) with a unit width
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(wpmText, gbc);

        // position cpmText at (1, 2) with a unit width
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(cpmText, gbc);

        // position accuracyText at (1, 3) with unit width
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(accuracyText, gbc);

        // position restartButton at (3, 1) with unit width
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        // gbc.weighty = 0.3;
        this.add(restarButton, gbc);
    }

    /**
     * Code to display grid lines on game over screen. Uncomment for debugging
     * purposes.
     */
    // @Override
    // public void paint(Graphics g) {
    // super.paint(g);
    // int[][] dims = layout.getLayoutDimensions();
    // g.setColor(Color.white);
    // int x = 0;
    // for (int add : dims[0]) {
    // x += add;
    // g.drawLine(x, 0, x, getHeight());
    // }
    // int y = 0;
    // for (int add : dims[1]) {
    // y += add;
    // g.drawLine(0, y, getWidth(), y);
    // }
    // }

    @Override
    protected void paintComponent(Graphics g) {
        // add background image to panel
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bg.jpg"));

        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }

    /**
     * Returns an icon found in resources folder.
     * 
     * @param path     Must start with / since path is relative to resources folder.
     * @param iconSize size of icon in pixels
     * @return
     */
    private ImageIcon loadIcon(String path, int iconSize) {
        return new ImageIcon(
                new ImageIcon(this.getClass().getResource(path)).getImage().getScaledInstance(iconSize,
                        iconSize,
                        Image.SCALE_DEFAULT));
    }

    /**
     * Displays time in timeTakenText JLabel.
     * 
     * @param time time in seconds.
     */
    public void setTimeTaken(long time) {
        if (time >= 0) {
            timeTakenText.setText(String.format("%d s", time));
        }
    }

    /**
     * Displays words per minute in wpmText JLabel.
     * 
     * @param wpm words per minute.
     */
    public void setWPM(long wpm) {
        wpmText.setText(String.format("%d wpm", wpm));
    }

    /**
     * Displays characters per minute in wpmText JLabel.
     * 
     * @param wpm characers per minute.
     */
    public void setCPM(long cpm) {
        cpmText.setText(String.format("%d cpm", cpm));
    }

    /**
     * Displays typing accuracy in accuracyText JLabel
     * 
     * @param acc A percentage representing typing accuracy.
     */
    public void setAccuracy(long acc) {
        if (acc < 0 || acc > 100) {
            System.out.println("Invalid accuracy");
        } else {
            accuracyText.setText(String.format("%d %%", acc));
        }
    }

    /***
     * Add action listener to restart button
     * 
     * @param newActionListener
     */
    public void addRestartButtonListener(ActionListener newActionListener) {
        restarButton.addActionListener(newActionListener);
    }

    public void drawChart(double[] timeData, double[] wpmData) {
        chart.updateSeries(timeData, wpmData);
        double average = Arrays.stream(wpmData).average().orElse(Double.NaN);
        chart.updateAverageWPM(average);
        chartPanel.repaint();
    }
}
