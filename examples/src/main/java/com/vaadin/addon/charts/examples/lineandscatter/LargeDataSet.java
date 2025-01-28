/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.examples.lineandscatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.ui.Component;

public class LargeDataSet extends AbstractVaadinChartExample {

    // one round produces bit more than thousand data
    private int ROUNDS = 4;

    @Override
    public String getDescription() {
        return "Vaadin Charts rendering engine can survive large data sets. "
                + "Especially if items have just values and no custom options. In this test "
                + (ROUNDS * TimeSeriesZoomable.USD_TO_EUR_EXCHANGE_RATES.length)
                + "data points are rendered.";
    }

    private boolean xyPairs() {
        return true;
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();

        chart.getConfiguration().setTitle("Large data set");

        // Force zoom and enable scrollbar
        chart.getConfiguration().getxAxis().setMin(5000);
        chart.getConfiguration().getScrollbar().setEnabled(true);

        DataSeries series = new DataSeries();
        Number[] data = TimeSeriesZoomable.USD_TO_EUR_EXCHANGE_RATES;
        Random r = new Random(0);
        int x = 0;
        for (int j = 0; j < ROUNDS; j++) {
            for (Number number : data) {
                DataSeriesItem item;
                if (xyPairs()) {
                    x += r.nextInt(4);
                    item = new DataSeriesItem(x, number);
                } else {
                    // interval data (x == index of data point), performs better
                    // and consumes less memory/bandwidth
                    item = new DataSeriesItem();
                    item.setY(number);
                }
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
                series.add(item);

            }
        }
        /*
         * If you play with this example your might try uncomment next line to
         * keep the amount of transfered data sane. You might also try to
         * increase the ROUNDS to increase the input data. By default commented
         * out to stress client side performance
         */
        // reduce(series, 1000);

        /*
         * If data can contain high/low peaks, one can use more sophisticated
         * algorithms
         */
        // ramerDouglasPeuckerReduce(series, 300);

        chart.getConfiguration().setSeries(series);

        PlotOptionsLine plotOptionsLine = new PlotOptionsLine();

        // Showing points with thousands of data items looks odd (on top of each
        // other), also renders faster without markers
        plotOptionsLine.setMarker(new Marker(false));
        // To render shadow, library must create additional element, without it
        // performance will be better
        plotOptionsLine.setShadow(false);
        plotOptionsLine.setAnimation(false);

        /*
         * If developers need to use large data sets and point specific
         * settings, they can override the default turbo threshold. Here we set
         * it to 200000 (default 1000). Turbo threshold is configuration that
         * works as a "sanity threshold" so that old browsers wont drop to their
         * knees under load. Without this Vaadin Charts might not render chart
         * if data items have e.g. name set.
         */
        plotOptionsLine.setTurboThreshold(200000);

        chart.getConfiguration().setPlotOptions(plotOptionsLine);

        return chart;
    }

    /**
     * An example how dataset can be reduced simply on the server side.
     *
     * <p>
     * Using more datapoints than there are pixels on the devices makes usually
     * no sense. Excess data just consumes bandwidth and client side CPU. This
     * method reduces a large dataset with a very simple method. If you scale up
     * data set by modifying the ROUNDS in this example and toggle passing it
     * through this method, you'll see that visually the chart don't change
     * much, but the amount of data and with huge amounts also the rendering
     * time drops dramatically.
     *
     * @param series
     *            the series to be reduced
     * @param pixels
     *            the pixel size for which the data should be adjusted
     */
    public static void simpleReduce(DataSeries series, int pixels) {
        DataSeriesItem first = series.get(0);
        DataSeriesItem last = series.get(series.size() - 1);
        ArrayList<DataSeriesItem> reducedDataSet = new ArrayList<DataSeriesItem>();
        if (first.getX() != null) {
            // xy pairs
            double startX = first.getX().doubleValue();
            double endX = last.getX().doubleValue();
            double minDistance = (endX - startX) / pixels;
            reducedDataSet.add(first);
            double lastPoint = first.getX().doubleValue();
            for (int i = 0; i < series.size(); i++) {
                DataSeriesItem item = series.get(i);
                if (item.getX().doubleValue() - lastPoint > minDistance) {
                    reducedDataSet.add(item);
                    lastPoint = item.getX().doubleValue();
                }
            }
            series.setData(reducedDataSet);
        } else {
            // interval data
            int k = series.size() / pixels;
            if (k > 1) {
                for (int i = 0; i < series.size(); i++) {
                    if (i % k == 0) {
                        DataSeriesItem item = series.get(i);
                        reducedDataSet.add(item);
                    }
                }
                series.setData(reducedDataSet);
            }
        }
    }

    /**
     * Reduce data series with bit more sophisticated method.
     *
     * @param series
     *            with x y data pairs
     * @param pixels
     */
    public static void ramerDouglasPeuckerReduce(DataSeries series, int pixels) {
        // Calculate rough estimate for visual ratio on x-y, might be bad guess
        // if axis have been set manually
        DataSeriesItem dataSeriesItem = series.get(0);
        double xMax = dataSeriesItem.getX().doubleValue();
        double xMin = xMax;
        double yMax = dataSeriesItem.getY().doubleValue();
        double yMin = yMax;
        for (int i = 1; i < series.size(); i++) {
            DataSeriesItem item = series.get(i);
            double x = item.getX().doubleValue();
            if (xMax < x) {
                xMax = x;
            }
            if (xMin > x) {
                xMin = x;
            }
            double y = item.getY().doubleValue();
            if (yMax < y) {
                yMax = y;
            }
            if (yMin > y) {
                yMin = y;
            }
        }
        double xyRatio = (xMax - xMin) / (yMax - yMin);

        // rough estimate for sane epsilon (1px)
        double epsilon = (xMax - xMin) / pixels;

        List<DataSeriesItem> rawData = series.getData();
        List<DataSeriesItem> reduced = ramerDouglasPeucker(rawData, epsilon,
                xyRatio);
        series.setData(reduced);

    }

    /**
     *
     * @see http://en.wikipedia.org/wiki/Ramer–Douglas–Peucker_algorithm
     *
     * @param points
     * @param epsilon
     * @param xyRatio
     *            y values are multiplied with this to make distance calculation
     *            in algorithm sane
     * @return
     */
    public static List<DataSeriesItem> ramerDouglasPeucker(
            List<DataSeriesItem> points, double epsilon, final double xyRatio) {
        // Find the point with the maximum distance
        double dmax = 0;
        int index = 0;
        DataSeriesItem start = points.get(0);
        DataSeriesItem end = points.get(points.size() - 1);

        for (int i = 1; i < points.size() - 1; i++) {
            DataSeriesItem point = points.get(i);
            double d = pointToLineDistance(start, end, point, xyRatio);
            if (d > dmax) {
                index = i;
                dmax = d;
            }
        }

        ArrayList<DataSeriesItem> reduced = new ArrayList<DataSeriesItem>();
        if (dmax >= epsilon) {
            // max distance is greater than epsilon, keep the most relevant
            // point and recursively simplify
            List<DataSeriesItem> startToRelevant = ramerDouglasPeucker(
                    points.subList(0, index + 1), epsilon, xyRatio);
            reduced.addAll(startToRelevant);
            List<DataSeriesItem> relevantToEnd = ramerDouglasPeucker(
                    points.subList(index, points.size() - 1), epsilon, xyRatio);
            reduced.addAll(relevantToEnd.subList(1, relevantToEnd.size()));
        } else {
            // no relevant points, drop all but ends
            reduced.add(start);
            reduced.add(end);
        }

        return reduced;
    }

    public static double pointToLineDistance(DataSeriesItem A,
            DataSeriesItem B, DataSeriesItem P, final double xyRatio) {
        double bY = B.getY().doubleValue() * xyRatio;
        double aY = A.getY().doubleValue() * xyRatio;
        double pY = P.getY().doubleValue() * xyRatio;
        double normalLength = Math.hypot(B.getX().doubleValue()
                - A.getX().doubleValue(), bY - aY);
        return Math.abs((P.getX().doubleValue() - A.getX().doubleValue())
                * (bY - aY) - (pY - aY)
                * (B.getX().doubleValue() - A.getX().doubleValue()))
                / normalLength;
    }

}
