package com.vaadin.addon.charts.demoandtestapp.lineandscatter;

import java.util.Random;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.SkipFromDemo;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.PlotLine;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

@SkipFromDemo
public class SplineUpdatingEachSecondWithCustomizedNewPoints extends
        AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Spline Updating Each Seconds";
    }

    @Override
    protected Component getChart() {
        final Random random = new Random();

        final Chart chart = new Chart();
        chart.setWidth("500px");

        final Configuration configuration = new Configuration();
        configuration.getChart().setType(ChartType.SPLINE);
        configuration.getTitle().setText("Live random data");

        Axis xAxis = configuration.getxAxis();
        xAxis.setType(AxisType.DATETIME);
        xAxis.setTickPixelInterval(150);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle(new Title("Value"));
        yAxis.setPlotLines(new PlotLine(0, 1, new SolidColor("#808080")));

        configuration.getTooltip().setEnabled(false);
        configuration.getLegend().setEnabled(false);

        final DataSeries series = new DataSeries();
        series.setPlotOptions(new PlotOptionsSpline());
        series.setName("Random data");
        for (int i = -19; i <= 0; i++) {
            DataSeriesItem item = new DataSeriesItem(System.currentTimeMillis()
                    + i * 1000, random.nextDouble());
            series.add(item);
        }

        runWhileAttached(chart, new Runnable() {

            @Override
            public void run() {
                final long x = System.currentTimeMillis();
                final double y = random.nextDouble();
                DataSeriesItem item = new DataSeriesItem(x, y);
                item.setName("Diipaiapa");
                Marker marker = new Marker();
                marker.setEnabled(true);
                boolean b = (new Random().nextInt(5) % 4 == 0);
                marker.setFillColor(new SolidColor(b ? "#ff0000" : "#000000"));
                item.setMarker(marker);
                series.add(item, true, true);
            }
        }, 1000, 1000);

        configuration.setSeries(series);

        chart.drawChart(configuration);
        return chart;
    }
}
