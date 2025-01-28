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
package com.vaadin.addon.charts.examples.marketing;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
@SkipFromDemo
public class ColumnSite extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Column demo used in charts site";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.COLUMN);

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Earnings");
        conf.setSubTitle("2011 - 2014");

        XAxis xAxis = conf.getxAxis();
        xAxis.setCategories("2011", "2012", "2013", "2014");

        YAxis yAxis = conf.getyAxis();
        yAxis.setTitle("B€");

        Tooltip tooltip = conf.getTooltip();
        tooltip.setPointFormat("{series.name}: {point.y} B€");

        conf.addSeries(new ListSeries("Revenue", 1.5, 1.8, 1.2, 2.3));
        conf.addSeries(new ListSeries("Expenses", 1.2, 1.1, 1.3, 0.9));
        conf.addSeries(new ListSeries("Net income", -0.3, 0.7, -0.1, 1.4));

        return chart;
    }

}
