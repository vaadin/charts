package com.vaadin.addon.charts.examples.combinations;

import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.HTMLLabelItem;
import com.vaadin.addon.charts.model.HTMLLabels;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class ColumnLineAndPie extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Column line and pie";
    }

    private void updateColorsFromTheme(Configuration configuration) {
        if (configuration == null) {
            return;
        }

        Color janeColor = getThemeColors()[0];
        Color johnColor = getThemeColors()[1];
        Color joeColor = getThemeColors()[2];

        // Colors for columns
        List<Series> series = configuration.getSeries();
        ((PlotOptionsColumn) ((DataSeries) series.get(0)).getPlotOptions())
                .setColor(janeColor);
        ((PlotOptionsColumn) ((DataSeries) series.get(1)).getPlotOptions())
                .setColor(johnColor);
        ((PlotOptionsColumn) ((DataSeries) series.get(2)).getPlotOptions())
                .setColor(joeColor);

        // Colors for the pie
        DataSeries pieSeries = ((DataSeries) series.get(4));
        pieSeries.get("Jane").setColor(janeColor);
        pieSeries.get("John").setColor(johnColor);
        pieSeries.get("Joe").setColor(joeColor);
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart() {
            @Override
            public void drawChart(Configuration conf) {
                ColumnLineAndPie.this.updateColorsFromTheme(conf);
                super.drawChart(conf);
            }
        };

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Combined Chart");

        conf.setExporting(true);
        conf.getExporting().setWidth(800);

        XAxis x = new XAxis();
        x.setCategories(new String[] { "Apples", "Oranges", "Pears", "Bananas",
                "Plums" });
        conf.addxAxis(x);

        Style labelStyle = new Style();
        labelStyle.setTop("8px");
        labelStyle.setLeft("40px");
        conf.setLabels(new HTMLLabels(labelStyle, new HTMLLabelItem(
                "Total fruit consumption")));

        DataSeries series = new DataSeries();
        PlotOptionsColumn plotOptions = new PlotOptionsColumn();
        series.setPlotOptions(plotOptions);
        series.setName("Jane");
        series.setData(3, 2, 1, 3, 4);
        conf.addSeries(series);

        series = new DataSeries();
        plotOptions = new PlotOptionsColumn();
        series.setPlotOptions(plotOptions);
        series.setName("John");
        series.setData(2, 3, 5, 7, 6);
        conf.addSeries(series);

        series = new DataSeries();
        plotOptions = new PlotOptionsColumn();
        series.setPlotOptions(plotOptions);
        series.setName("Joe");
        series.setData(4, 3, 3, 9, 0);
        conf.addSeries(series);

        series = new DataSeries();
        PlotOptionsSpline splinePlotOptions = new PlotOptionsSpline();
        Marker marker = new Marker();
        marker.setLineWidth(2);
        marker.setLineColor(new SolidColor("black"));
        marker.setFillColor(new SolidColor("white"));
        splinePlotOptions.setMarker(marker);
        splinePlotOptions.setColor(new SolidColor("black"));
        series.setPlotOptions(splinePlotOptions);
        series.setName("Average");
        series.setData(3, 2.67, 3, 6.33, 3.33);
        conf.addSeries(series);

        series = new DataSeries();
        series.setPlotOptions(new PlotOptionsPie());
        series.setName("Total consumption");
        DataSeriesItem item = new DataSeriesItem("Jane", 13);
        series.add(item);
        item = new DataSeriesItem("John", 23);
        series.add(item);
        item = new DataSeriesItem("Joe", 19);
        series.add(item);

        PlotOptionsPie plotOptionsPie = new PlotOptionsPie();
        plotOptionsPie.setSize("100px");
        plotOptionsPie.setCenter("100px", "80px");
        plotOptionsPie.setShowInLegend(false);
        plotOptionsPie.setShowInLegend(false);
        series.setPlotOptions(plotOptionsPie);
        conf.addSeries(series);

        chart.drawChart(conf);
        return chart;
    }
}
