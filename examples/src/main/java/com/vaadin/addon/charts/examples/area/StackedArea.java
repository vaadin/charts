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

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsArea;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.Stacking;
import com.vaadin.addon.charts.model.Subtitle;
import com.vaadin.addon.charts.model.TickmarkPlacement;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class StackedArea extends AbstractVaadinChartExample {

    private static final SolidColor GRAY = new SolidColor("#666666");

    @Override
    public String getDescription() {
        return "Stacked area";
    }

    @Override
    protected Component getChart() {

        Chart chart = new Chart(ChartType.AREA);

        Configuration conf = chart.getConfiguration();

        conf.setTitle(new Title(
                "Historic and Estimated Worldwide Population Growth by Region"));
        conf.setSubTitle(new Subtitle("Source: Wikipedia.org"));

        XAxis xAxis = new XAxis();
        xAxis.setTickmarkPlacement(TickmarkPlacement.ON);
        xAxis.setCategories("1750", "1800", "1850", "1900", "1950", "1999",
                "2050");
        conf.addxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setTitle(new AxisTitle("Billions"));
        Labels labels = new Labels();
        labels.setFormatter("this.value / 1000");
        yAxis.setLabels(labels);
        conf.addyAxis(yAxis);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("this.x +': '+ Highcharts.numberFormat(this.y, 0, ',') +' millions'");
        conf.setTooltip(tooltip);

        PlotOptionsArea plotOptions = new PlotOptionsArea();
        plotOptions.setStacking(Stacking.NORMAL);
        conf.setPlotOptions(plotOptions);

        List<Series> series = new ArrayList<Series>();
        series.add(new ListSeries("Asia", 502, 635, 809, 947, 1402, 3634, 5268));
        series.add(new ListSeries("Africa", 106, 107, 111, 133, 221, 767, 1766));
        series.add(new ListSeries("Europe", 163, 203, 276, 408, 547, 729, 628));
        series.add(new ListSeries("America", 18, 31, 54, 156, 339, 818, 1201));
        series.add(new ListSeries("Ocenia", 2, 2, 2, 6, 13, 30, 46));
        conf.setSeries(series);

        chart.drawChart(conf);

        return chart;

    }
}
