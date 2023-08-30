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
package com.vaadin.addon.charts.examples.lineandscatter;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.ui.Component;

/**
 * Shows a line diagram with custom tick positions on both axes.
 *
 */
@SkipFromDemo
public class BasicLineWithTickPositions extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Basic chart with customized tick positions";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();
        chart.setId("chart");
        Configuration config = chart.getConfiguration();
        config.setTitle("Customized crosshairs");
        config.getxAxis().setTickPositions(new Integer[] { 1, 2, 4, 8, 16 });
        config.getxAxis().setGridLineWidth(1);
        config.getyAxis().setTickPositions(
                new Integer[] { 0, 160, 240, 280, 300, 310 });

        ListSeries ls = new ListSeries();
        ls.setName("Data");
        ls.setData(29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4,
                194.1, 95.6, 54.4);

        config.setSeries(ls);

        chart.drawChart(config);

        return chart;
    }

}
