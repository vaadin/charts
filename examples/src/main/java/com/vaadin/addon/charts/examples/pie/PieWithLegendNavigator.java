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
package com.vaadin.addon.charts.examples.pie;

import static com.vaadin.addon.charts.model.HorizontalAlign.RIGHT;
import static com.vaadin.addon.charts.model.LayoutDirection.VERTICAL;
import static com.vaadin.addon.charts.model.VerticalAlign.TOP;
import static com.vaadin.addon.charts.model.style.SolidColor.GREEN;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.LegendNavigation;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
@SkipFromDemo
public class PieWithLegendNavigator extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Pie with legend";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.PIE);

        Configuration conf = chart.getConfiguration();
        Legend legend = conf.getLegend();
        legend.setLayout(VERTICAL);
        legend.setAlign(RIGHT);
        legend.setVerticalAlign(TOP);
        LegendNavigation nav = legend.getNavigation();
        nav.setActiveColor(GREEN);
        nav.setArrowSize(24);

        conf.setTitle("Lot of slices to force navigation in legend");

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.getDataLabels().setEnabled(false);
        plotOptions.setShowInLegend(true);
        conf.setPlotOptions(plotOptions);

        DataSeries series = new DataSeries();
        for (int i = 0; i < 50; i++) {
            series.add(new DataSeriesItem("Item " + i, 1));
        }
        conf.addSeries(series);
        return chart;
    }

}
