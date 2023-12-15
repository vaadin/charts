/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.examples.pie;

import static com.vaadin.addon.charts.model.ChartType.PIE;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
@SkipFromDemo
public class PieChartCursors extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Pie chart with custom cursors";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(PIE);

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Browser market shares at a specific website, 2010");

        final DataSeries series = new DataSeries();
        DataSeriesItem item = new DataSeriesItem("Firefox", 45.0);
        item.setCursor("no-drop");
        series.add(item);
        item = new DataSeriesItem("IE", 26.8);
        item.setCursor("none");
        series.add(item);
        DataSeriesItem chrome = new DataSeriesItem("Chrome", 12.8);
        chrome.setCursor("progress");
        series.add(chrome);
        item = new DataSeriesItem("Safari", 8.5);
        item.setCursor("pointer");
        series.add(item);
        item = new DataSeriesItem("Opera", 6.2);
        item.setCursor("move");
        series.add(item);
        item = new DataSeriesItem("Others", 0.7);
        item.setCursor("copy");
        series.add(item);
        conf.setSeries(series);

        return chart;
    }

}
