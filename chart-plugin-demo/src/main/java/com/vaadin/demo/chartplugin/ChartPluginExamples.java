package com.vaadin.demo.chartplugin;

import java.util.Locale;
import java.util.Random;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartModel;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.ZoomType;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.demo.chartplugin.model.CustomChartTypes;
import com.vaadin.demo.chartplugin.model.MapSeries;
import com.vaadin.demo.chartplugin.model.ValueRange;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ChartPluginExamples extends VerticalLayout {

    public ChartPluginExamples() {
        setMargin(true);
        setSpacing(true);
        addComponent(new Label(
                "<h1>Chart plugins</h1>This example shows how to use a custom Highcharts plugin. " +
                        "This example uses Vaadin Charts 2.0.0 with a custom heat map chart type",
                ContentMode.HTML));
        addComponent(createHeatMap());
    }

    public static Chart createHeatMap() {
        final Chart chart = new Chart(CustomChartTypes.MAP);
        chart.setWidth("800px");
        chart.setHeight("500px");

        final Configuration configuration = chart.getConfiguration();
        ChartModel model = new ChartModel();
        configuration.setChart(model);

        model.setType(CustomChartTypes.MAP);
        model.setBorderWidth(1);
        model.setZoomType(ZoomType.XY);
        model.setInverted(false);

        configuration.getTitle().setText(
                "Vaadin Charts Test for complex highcharts plugin");

        XAxis xAxis = configuration.getxAxis();
        xAxis.setEndOnTick(false);
        xAxis.setGridLineWidth(0);
        xAxis.getLabels().setEnabled(false);
        xAxis.setLineWidth(0);
        xAxis.setMinPadding(0);
        xAxis.setMaxPadding(0);
        xAxis.setStartOnTick(false);
        xAxis.setTickWidth(0);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setEndOnTick(false);
        yAxis.setGridLineWidth(0);
        yAxis.getLabels().setEnabled(false);
        yAxis.setLineWidth(0);
        yAxis.setMinPadding(0);
        yAxis.setMaxPadding(0);
        yAxis.setStartOnTick(false);
        yAxis.setTickWidth(0);
        yAxis.setTitle("");
        yAxis.setReversed(true);

        Legend legend = configuration.getLegend();
        legend.setAlign(HorizontalAlign.LEFT);
        legend.setVerticalAlign(VerticalAlign.BOTTOM);
        legend.setFloating(true);
        legend.setLayout(LayoutDirection.VERTICAL);
        configuration.setExporting(false);

        MapSeries series = new MapSeries();

        series.addValueRange(new ValueRange(null, 3, new SolidColor(19, 64,
                117, 0.05)));
        series.addValueRange(new ValueRange(3, 10, new SolidColor(19, 64, 117,
                0.2)));
        series.addValueRange(new ValueRange(10, 30, new SolidColor(19, 64, 117,
                0.4)));
        series.addValueRange(new ValueRange(30, 100, new SolidColor(19, 64,
                117, 0.5)));
        series.addValueRange(new ValueRange(100, 300, new SolidColor(19, 64,
                117, 0.6)));
        series.addValueRange(new ValueRange(300, 1000, new SolidColor(19, 64,
                117, 0.8)));
        series.addValueRange(new ValueRange(1000, null, new SolidColor(19, 64,
                117, 1)));

        Random random = new Random();
        for (String c : Locale.getISOCountries()) {
            DataSeriesItem p = new DataSeriesItem(c.toLowerCase(),
                    random.nextInt(1200));
            series.add(p);
        }
        configuration.addSeries(series);

        chart.drawChart(configuration);
        return chart;
    }
}
