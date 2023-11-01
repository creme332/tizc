package view;

import java.awt.Color;
import java.awt.Font;

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
    private Font labelFont = myFont.Regular.deriveFont(12f);

    public WPMChart() {
        chart = new XYChartBuilder()
                .width(800)
                .height(200)
                .xAxisTitle("Time")
                .yAxisTitle("Words per Minute")
                .build();

        // chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);

        chart.getStyler().setPlotGridLinesVisible(false); // hide grid lines
        chart.getStyler().setMarkerSize(10);
        chart.getStyler().setPlotBorderColor(new Color(0, 0, 0, 0));

        // make chart transparent
        chart.getStyler().setChartBackgroundColor(new Color(0, 0, 0, 0));
        chart.getStyler().setPlotBackgroundColor(new Color(0, 0, 0, 0));

        chart.getStyler().setLegendVisible(false);

        // enable tooltips
        // chart.getStyler().setToolTipsEnabled(true);

        chart.getStyler().setYAxisMin(0.0);
        chart.getStyler().setYAxisTickMarksColor(Color.WHITE);
        chart.getStyler().setAxisTickLabelsColor(Color.WHITE);
        chart.getStyler().setChartFontColor(Color.WHITE);

        // set font
        chart.getStyler().setAxisTitleFont(labelFont);
        chart.getStyler().setAxisTickLabelsFont(labelFont);

        // add a default series
        updateSeries(new double[] { 1, 2, 3, 4 }, new double[] { 110, 120, 133, 90 });
    }

    public XYChart get() {
        return chart;
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
        series.setLineColor(new Color(147, 232, 211));
        series.setLineWidth(3f);
        series.setMarkerColor(new Color(147, 232, 211));

    }
}
