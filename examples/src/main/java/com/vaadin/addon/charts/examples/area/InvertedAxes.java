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
package com.vaadin.addon.charts.examples.area;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsArea;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class InvertedAxes extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Inverted Axes";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.AREA);

        Configuration conf = chart.getConfiguration();

        conf.getChart().setInverted(true);

        conf.setTitle(new Title("Average fruit consumption during one week"));

        XAxis xAxis = new XAxis();
        xAxis.setCategories("Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday", "Saturday", "Sunday");
        conf.addxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setTitle(new AxisTitle("Number of units"));
        yAxis.setMin(0);
        conf.addyAxis(yAxis);

        Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setAlign(HorizontalAlign.LEFT);
        legend.setFloating(true);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setX(-150);
        legend.setY(100);
        legend.setBorderWidth(1);
        legend.setBackgroundColor(new SolidColor("#ffffff"));
        conf.setLegend(legend);

        PlotOptionsArea plotOptions = new PlotOptionsArea();
        plotOptions.setFillOpacity(0.5);
        conf.setPlotOptions(plotOptions);

        conf.addSeries(new ListSeries("John", 3, 4, 3, 5, 4, 10, 12));
        conf.addSeries(new ListSeries("Jane", 1, 3, 4, 3, 3, 5, 4));

        chart.drawChart(conf);

        return chart;
    }

}
