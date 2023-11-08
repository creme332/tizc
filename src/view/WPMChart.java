package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.knowm.xchart.AnnotationLine;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;

import utils.PoppinsFont;

public class WPMChart {
    private String seriesName = "game";
    private XYChart chart;
    private PoppinsFont myFont = new PoppinsFont();
    private Font labelFont = myFont.Regular.deriveFont(16f);
    private AnnotationLine xLine = new AnnotationLine(10, false, false);

    private Color greenColour = new Color(147, 232, 211);

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
        chart.getStyler().setPlotBorderColor(new Color(0, 0, 0, 0));
        chart.getStyler().setChartBackgroundColor(new Color(0, 0, 0, 0));
        chart.getStyler().setPlotBackgroundColor(new Color(0, 0, 0, 0));
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
        chart.getStyler().setAnnotationLineColor(greenColour);

        // make annotation line dashed: https://stackoverflow.com/q/21989082/17627866
        chart.getStyler().setAnnotationLineStroke(
                new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 0, new float[] { 5 }, 0));

        // change formatting of xAxis tick labels
        chart.getStyler().setxAxisTickLabelsFormattingFunction(x -> ((x.longValue() % 5) == 0) ? "y" : "x");

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
            // TODO: handle exception
        }
    }

    public void updateSeries(double[] timeData, double[] wpmData) {
        chart.removeSeries(seriesName);
        XYSeries series = chart.addSeries(seriesName, timeData, wpmData);

        // customise series
        series.setLineColor(greenColour);
        series.setLineWidth(3f);
        series.setMarkerColor(greenColour);
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
