package com.github.creme332.view.GameOver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import com.github.creme332.utils.IconLoader;
import com.github.creme332.utils.PoppinsFont;

/**
 * Screen displayed when game ends.
 */
public class GameOverScreen extends JPanel {
    private JButton restartButton;
    private JButton homeButton;

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
        IconLoader loader = new IconLoader();
        float mediumFontSize = 50; // font size to be used in JLabels
        int iconSize = 50;

        GridBagConstraints gbc = new GridBagConstraints();
        layout = new GridBagLayout();
        this.setLayout(layout);

        ImageIcon timerIcon, speedometerIcon, accuracyIcon;

        try {
            timerIcon = loader.loadIcon("/icon/deadline.png", iconSize);
            speedometerIcon = loader.loadIcon("/icon/speedometer.png", iconSize);
            accuracyIcon = loader.loadIcon("/icon/accuracy.png", iconSize);
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        JPanel buttonContainer = new JPanel(new FlowLayout());

        // styles for buttonContainer
        buttonContainer.setOpaque(false);

        // create buttons
        restartButton = createStyledButton("Restart");
        homeButton = createStyledButton("Home");

        // add buttons to container
        buttonContainer.add(restartButton);
        buttonContainer.add(Box.createHorizontalStrut(50));
        buttonContainer.add(homeButton);

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
        gbc.gridwidth = 4;
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

        // position buttonContainer at (3, 1) with width 2
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        this.add(buttonContainer, gbc);

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
        ImageIcon img = new ImageIcon();

        try {
            img = new IconLoader().loadIcon("/bg.jpg");
        } catch (Exception e) {
            System.out.println(e);
        }

        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }

    /**
     * Creates a JButton with some styles
     * 
     * @param text Text inside button
     * @return Styled button
     */
    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        PoppinsFont myFont = new PoppinsFont();

        // styles for restartButton
        btn.setFocusPainted(false);
        btn.setFont(myFont.Regular.deriveFont(30f));
        btn.setContentAreaFilled(false);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(Color.white));
        btn.setPreferredSize(new Dimension(300, 60));

        return btn;
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
     * @param wpm characters per minute.
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

    /**
     * Add action listener to restart button
     * 
     * @param newActionListener
     */
    public void addRestartButtonListener(ActionListener newActionListener) {
        restartButton.addActionListener(newActionListener);
    }

    /***
     * Add action listener to home button
     * 
     * @param newActionListener
     */
    public void addHomeButtonListener(ActionListener newActionListener) {
        homeButton.addActionListener(newActionListener);
    }

    public void drawChart(double[] timeData, double[] wpmData) {
        chart.updateSeries(timeData, wpmData);
        double average = Arrays.stream(wpmData).average().orElse(Double.NaN);
        chart.updateAverageWPM(average);
        chartPanel.repaint();
    }
}
