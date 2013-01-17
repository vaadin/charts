package com.vaadin.addon.charts.demoandtestapp.lineandscatter;

import java.util.Random;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.ui.Component;

public class LargeDataSet extends AbstractVaadinChartExample {

    private int ROUNDS = 4;

    @Override
    public String getDescription() {
        return "Highcharts rendering engine can survice large data sets. "
                + "Especially if items have just values and no custom options. In this test "
                + (ROUNDS * TimeSeriesZoomable.USD_TO_EUR_EXCHANGE_RATES.length)
                + "data points are rendered.";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();

        DataSeries series = new DataSeries();
        Number[] data = TimeSeriesZoomable.USD_TO_EUR_EXCHANGE_RATES;
        Random r = new Random(0);
        int x = 0;
        for (int j = 0; j < ROUNDS; j++) {
            for (Number number : data) {
                x += r.nextInt(4);
                DataSeriesItem item = new DataSeriesItem(x, number);
                /*
                 * Note, with large datasets, avoid settings like
                 * item.setColor(myColorX), or item.setName(myName) for data
                 * items. Without them the framework is able to optimize the
                 * rendering and can survive from rather large datasets.
                 * 
                 * Also note, that if data set is very large, the library might
                 * ignore it if there are custom settings for data items. This
                 * threshold is called turboThreshHold in plot options. See
                 * example below.
                 */
                // item.setName("x " + x);
                series.addData(item);

            }
        }
        chart.getConfiguration().setSeries(series);

        PlotOptionsLine plotOptionsLine = new PlotOptionsLine();

        // Showing points with thousands of data items looks odd (on top of each
        // other)
        plotOptionsLine.setMarker(new Marker(false));
        plotOptionsLine.setAnimation(false);

        /*
         * If developers need to use large data sets and point specific
         * settings, they can override the default turbo threshold. Here we set
         * it to 200000 (default 1000). Without this Highcharts might not render
         * chart if data items has e.g. name set.
         */
        plotOptionsLine.setTurboThreshold(200000);

        chart.getConfiguration().setPlotOptions(plotOptionsLine);

        // System.out.println(chart.getConfiguration());

        return chart;
    }

}
