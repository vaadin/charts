package com.vaadin.addon.charts.examples.lineandscatter;

import java.util.Random;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotLine;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

@SkipFromDemo
public class SplineUpdatingEachSecondWithTwoLines extends
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

        final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.SPLINE);
        configuration.getTitle().setText("Live random data");

        XAxis xAxis = configuration.getxAxis();
        xAxis.setType(AxisType.DATETIME);
        xAxis.setTickPixelInterval(150);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle(new AxisTitle("Value"));
        PlotLine plotline = new PlotLine();
        plotline.setValue(0);
        plotline.setWidth(1);
        plotline.setColor(new SolidColor("#808080"));
        yAxis.setPlotLines(new PlotLine[] { plotline });

        // FIXME missing generated API
        configuration.getTooltip().setEnabled(false);
        configuration.getLegend().setEnabled(false);

        final DataSeries series = new DataSeries();
        series.setPlotOptions(new PlotOptionsSpline());
        series.setName("Random data");
        for (int i = -19; i <= 0; i++) {
            series.add(new DataSeriesItem(
                    System.currentTimeMillis() + i * 1000, random.nextDouble()));
        }
        final DataSeries series2 = new DataSeries();
        series2.setPlotOptions(new PlotOptionsSpline());
        series2.setName("Random data");
        for (int i = -19; i <= 0; i++) {
            series2.add(new DataSeriesItem(System.currentTimeMillis() + i
                    * 1000, random.nextDouble()));
        }
        runWhileAttached(chart, new Runnable() {
            @Override
            public void run() {
                long x = System.currentTimeMillis();
                series.add(new DataSeriesItem(x, random.nextDouble()), true,
                        true);
                series2.add(new DataSeriesItem(x, random.nextDouble()), true,
                        true);

            }
        }, 1000, 1000);

        configuration.setSeries(series, series2);

        chart.drawChart(configuration);
        return chart;
    }
}
