package com.github.creme332.view.GameOver;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;

import org.knowm.xchart.AnnotationLine;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;

public class WPMChart {
    private String seriesName = "game";
    private Font labelFont = UIManager.getFont("medium.font");
    private Color greenColor = new Color(147, 232, 211);
    private Color transparent = new Color(0, 0, 0, 0);

    private XYChart chart;
    private AnnotationLine xLine = new AnnotationLine(10, false, false);

    public WPMChart() {
        chart = new XYChartBuilder()
                .width(700)
                .height(400)
                .xAxisTitle("Time")
                .yAxisTitle("Words per Minute")
                .build();

        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);

        chart.getStyler().setPlotGridLinesVisible(false); // hide grid lines
        chart.getStyler().setMarkerSize(10);

        // make chart transparent
        chart.getStyler().setPlotBorderColor(transparent);
        chart.getStyler().setChartBackgroundColor(transparent);
        chart.getStyler().setPlotBackgroundColor(transparent);
        chart.getStyler().setChartFontColor(Color.WHITE);

        // hide legend
        chart.getStyler().setLegendVisible(false);

        // enable tooltips
        chart.getStyler().setToolTipsEnabled(true);
        chart.getStyler().setToolTipBackgroundColor(Color.BLACK);
        chart.getStyler().setToolTipFont(labelFont);

        // style axes
        chart.getStyler().setYAxisMin(0.0);
        chart.getStyler().setYAxisTickMarksColor(Color.WHITE);
        chart.getStyler().setAxisTickLabelsColor(Color.WHITE);

        // set font
        chart.getStyler().setAxisTitleFont(labelFont);
        chart.getStyler().setAxisTickLabelsFont(labelFont);

        // style annotation line
        chart.getStyler().setAnnotationLineColor(greenColor);

        // make annotation line dashed: https://stackoverflow.com/q/21989082/17627866
        chart.getStyler().setAnnotationLineStroke(
                new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 0, new float[] { 5 }, 0));

        // round off displayed values for y tick label. (useful for tooltip)
        chart.getStyler().setyAxisTickLabelsFormattingFunction(y -> String.valueOf(y.intValue()));

        // add annotation line
        chart.addAnnotation(xLine);

        // plot random data on graph for testing purposes
        plotRandomData(60);
    }

    public XYChart get() {
        return chart;
    }

    /**
     * Plots random data on graph and updates annotation line appropriately.
     *
     * @param numPoints Dataset size
     */
    private void plotRandomData(int numPoints) {
        double[] y = new double[numPoints];
        double sum = 0;
        y[0] = 0;

        for (int i = 1; i < y.length; i++) {
            y[i] = 50 * Math.random() + 30;
            sum += y[i];
        }
        updateSeries(null, y);
        updateAverageWPM(sum / numPoints);
    }

    public void saveImage() {
        // Save it
        try {
            BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapFormat.PNG);

            // or save it in high-res
            BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI",
                    BitmapFormat.PNG, 300);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Updates series data displayed on chart
     * 
     * @param timeData Time array. Can be set to null to use sequence 1..n, where n
     *                 is the size of wpmData
     * @param wpmData  array of WPM data.
     */
    public void updateSeries(double[] timeData, double[] wpmData) {
        chart.removeSeries(seriesName);
        XYSeries series = chart.addSeries(seriesName, timeData, wpmData);

        // customize series
        series.setLineColor(greenColor);
        series.setLineWidth(3f);
        series.setMarkerColor(greenColor);
    }

    /**
     * Updates position of horizontal line indicating average WPM
     * 
     * @param value new average WPM
     */
    public void updateAverageWPM(double value) {
        xLine.setValue(value);
    }
}
