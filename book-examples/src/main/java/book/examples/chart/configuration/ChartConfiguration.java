/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package book.examples.chart.configuration;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.PointOptions;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.Stacking;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.FontWeight;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;

public class ChartConfiguration {

    public void plotOptionsSnippet1(Configuration conf) {
        Chart chart = new Chart();
        Configuration configuration = chart.getConfiguration();
        PlotOptionsColumn plotOptions = new PlotOptionsColumn();
        plotOptions.setStacking(Stacking.NORMAL);
        configuration.setPlotOptions(plotOptions);
    }

    public void plotOptionsSnippet2(Configuration conf) {
        ListSeries series = new ListSeries(50, 60, 70, 80);
        PlotOptionsColumn plotOptions = new PlotOptionsColumn();
        plotOptions.setStacking(Stacking.NORMAL);
        series.setPlotOptions(plotOptions);
    }

    private void setCommonProperties(PointOptions options) {
       options.setLineWidth(5);
       options.setColor(SolidColor.RED);
       options.setAnimation(false);
    }

    public void plotOptionsSnippet3(Configuration configuration) {
       PlotOptionsSpline lineOptions = new PlotOptionsSpline();
       PlotOptionsLine splineOptions = new PlotOptionsLine();
       setCommonProperties(lineOptions);
       setCommonProperties(splineOptions);
       configuration.setPlotOptions(lineOptions, splineOptions);
    }

    public void axisSnippet1(Configuration conf) {
        XAxis xaxis = new XAxis();
        xaxis.setTitle("Axis title");
        conf.addxAxis(xaxis);
    }

    public void labelsSnippet1(Configuration conf) {
        XAxis xaxis = new XAxis();
        // ...
        Labels xlabels = xaxis.getLabels();
        xlabels.setAlign(HorizontalAlign.CENTER); // Default
        xlabels.getStyle().setColor(SolidColor.GREEN);
        xlabels.getStyle().setFontWeight(FontWeight.BOLD);
        xlabels.setRotation(-45);
        xlabels.setStep(2); // Every 2 major tick
    }

    public void labelsSnippet2(XAxis xaxis) {
        Labels xlabels = xaxis.getLabels();
        xlabels.setAlign(HorizontalAlign.RIGHT);
        xlabels.setRotation(-45); // Tilt 45 degrees CCW
    }

    public void labelsSnippet3(XAxis xaxis) {
        Labels xlabels = xaxis.getLabels();
        xlabels.setStep(2); // Every 2 major tick
    }

    public void labelsSnippet4(XAxis xaxis) {
        Labels xlabels = xaxis.getLabels();
        Style xlabelsstyle = new Style();
        xlabelsstyle.setColor(SolidColor.GREEN);
        xlabels.setStyle(xlabelsstyle);
    }

    public void legendSnippet1(Configuration configuration) {
        Legend legend = configuration.getLegend();
        legend.getTitle().setText("City");
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setAlign(HorizontalAlign.LEFT);
        legend.setVerticalAlign(VerticalAlign.TOP);
    }

    public void formattingSnippet1(Configuration configuration) {
        YAxis yaxis = new YAxis();
        Labels ylabels = yaxis.getLabels();
        ylabels.setFormatter("function() {return this.value + ' km';}");
    }

}
