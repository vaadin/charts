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
package com.vaadin.addon.charts.examples.other;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.ui.Component;


@SkipFromDemo
public class LegendMarginTestUI extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "There should be a 100px margin between chart and legend.";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.LINE);
        chart.setHeight("450px");
        chart.setWidth("100%");

        chart.getConfiguration().addSeries(new ListSeries(1, 2, 3));

        Legend legend=chart.getConfiguration().getLegend();
        legend.setMargin(100);
        return chart;
    }

}
