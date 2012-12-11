package com.vaadin.addon.charts.demoandtestapp.lineandscatter;

import java.util.Random;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.dynamic.Refresher;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotLine;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

public class SplineUpdatingEachSecond extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Spline Updating Each Seconds";
    }

    @Override
    protected Component getChart() {
        final Random random = new Random();

        Refresher refresher = new Refresher();
        addComponent(refresher);

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
            series.addData(new DataSeriesItem(System.currentTimeMillis() + i
                    * 1000, random.nextDouble()));
        }
        Thread randomDataGenerator = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        sleep(1000);
                        if (chart.isConnectorEnabled()) {
                            long x = System.currentTimeMillis();
                            double y = random.nextDouble();
                            getSession().lock();
                            try {
                                series.addData(new DataSeriesItem(x, y));
                                // chart.addPoint(x, y, 0);
                            } finally {
                                getSession().unlock();
                            }
                        } else {
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        randomDataGenerator.start();

        configuration.setSeries(series);

        chart.drawChart(configuration);
        return chart;
    }
}
