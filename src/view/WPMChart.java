package view;

import java.awt.Color;
import java.awt.Font;
import java.util.function.Function;

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
    private Font labelFont = myFont.Regular.deriveFont(12f);
    private AnnotationLine xLine = new AnnotationLine(10, false, false);

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

        // hide legend
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

        chart.getStyler().setXAxisMaxLabelCount(5);

        Function<Double, String> function = (Double d) -> {
            int a = d.intValue();
            if (a % 2 == 0) {
                System.out.println(a);
                return String.valueOf(a);
            }
            return "x";
        };

        // style annotation line
        chart.getStyler().setAnnotationLineColor(Color.white);

        // customize spacing of xtick labels
        // chart.getStyler().setxAxisTickLabelsFormattingFunction(null);
        // chart.getStyler().setXAxisTickMarkSpacingHint(1);

        // chart.getStyler().setxAxisTickLabelsFormattingFunction(function);
        // chart.setCustomXAxisTickLabelsFormatter(x->"x");

        chart.getStyler().setxAxisTickLabelsFormattingFunction(x -> x.longValue() % 2 == 0 ? "y" : "x");

        // add a default series
        updateSeries(new double[] { 1, 2, 3, 4, 5, 6 },
                new double[] { 60, 53, 56, 55, 61, 64 });

        chart.addAnnotation(xLine);
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

    /**
     * Displays horizontal line
     */
    public void updateAverageWPM(double value) {
        xLine.setValue(value);
    }
}
