package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.colors.XChartSeriesColors;

import utils.PoppinsFont;

/**
 * Screen displayed when game ends.
 */
public class GameOverScreen extends JPanel {
    private JButton restarButton = new JButton("Restart");
    private JLabel gameOverText = new JLabel("Game Over");
    private JLabel timeTakenText = new JLabel();
    private JLabel wpmText = new JLabel();
    private JLabel accuracyText = new JLabel();
    public static String name = "gameOverScreen";

    // Create Chart
    private XYChart chart = new XYChartBuilder().width(800).height(600).title("Typing speed").xAxisTitle("Time")
            .yAxisTitle("WPM").build();
    JPanel chartPanel;

    public GameOverScreen() {
        PoppinsFont myFont = new PoppinsFont();
        float mediumFontSize = 50; // font size to be used in JLabels
        int iconSize = 50;

        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        ImageIcon timerIcon = new ImageIcon(
                new ImageIcon("resources/icon/deadline.png").getImage().getScaledInstance(iconSize, iconSize,
                        Image.SCALE_DEFAULT));
        ImageIcon speedometerIcon = new ImageIcon(
                new ImageIcon("resources/icon/speedometer.png").getImage().getScaledInstance(iconSize, iconSize,
                        Image.SCALE_DEFAULT));
        ImageIcon accuracyIcon = new ImageIcon(
                new ImageIcon("resources/icon/accuracy.png").getImage().getScaledInstance(iconSize, iconSize,
                        Image.SCALE_DEFAULT));

        // styles for gameOverText
        gameOverText.setFont(myFont.Bold.deriveFont(70f));
        gameOverText.setHorizontalAlignment(JLabel.CENTER);
        gameOverText.setForeground(Color.WHITE);

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

        // set global gbc constraints
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // position gameOverText at (0, 0) with a width of 3 cells
        gbc.gridx = 0; // column
        gbc.gridy = 0; // row
        gbc.gridwidth = 3;
        gbc.weighty = 0.3;

        this.add(gameOverText, gbc);

        gbc.weighty = 1;

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

        // position accuracyText at (1, 2) with unit width
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(accuracyText, gbc);

        // position restartButton at (2, 1) with unit width
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weighty = 0.3;
        this.add(restarButton, gbc);

        // Customize Chart
        // chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
        chart.getStyler().setChartBackgroundColor(new Color(0, 0, 0, 0));
        chart.getStyler().setPlotBackgroundColor(new Color(0, 0, 0, 0));
        chart.getStyler().setToolTipsEnabled(true);
        // chart.getStyler().setPlotMargin(0);
        // chart.getStyler().setPlotContentSize(.95);
        chart.getStyler().setYAxisMin(0.0);
        chart.getStyler().setYAxisTickMarksColor(Color.WHITE);
        chart.getStyler().setAxisTickLabelsColor(Color.WHITE);

        // set font
        chart.getStyler().setAxisTitleFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        chart.getStyler().setAxisTickLabelsFont(new Font(Font.SERIF, Font.PLAIN, 20));

        chartPanel = new XChartPanel<XYChart>(chart);
        chartPanel.setOpaque(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        this.add(chartPanel, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // add background image to panel
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("resources/bg.jpg");

        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
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

        chart.removeSeries("Typing speed");

        XYSeries series = chart.addSeries("Typing speed", timeData, wpmData);
        series.setLineColor(Color.PINK);

        // turn off series marker
        chartPanel.repaint();
    }
}
