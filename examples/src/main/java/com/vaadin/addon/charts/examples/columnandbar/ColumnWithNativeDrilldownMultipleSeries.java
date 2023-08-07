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
package com.vaadin.addon.charts.examples.columnandbar;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
@SkipFromDemo
public class ColumnWithNativeDrilldownMultipleSeries extends
        AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Column chart with eager loading drilldown and multiple series";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.COLUMN);
        chart.setId("chart");

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Browser market share, April, 2011");
        conf.setSubTitle("Click the columns to view versions. Click again to view brands.");
        conf.getLegend().setEnabled(false);

        XAxis x = new XAxis();
        x.setType(AxisType.CATEGORY);
        conf.addxAxis(x);

        YAxis y = new YAxis();
        y.setTitle("Total percent market share");
        conf.addyAxis(y);

        PlotOptionsColumn column = new PlotOptionsColumn();
        column.setCursor(Cursor.POINTER);
        column.setDataLabels(new DataLabels(true));
        column.getDataLabels().setFormatter("this.y +'%'");

        conf.setPlotOptions(column);

        Tooltip tooltip = new Tooltip();
        tooltip.setHeaderFormat("<span style=\"font-size:11px\">{series.name}</span><br>");
        tooltip.setPointFormat("<span style=\"color:{point.color}\">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>");
        conf.setTooltip(tooltip);

        createSeriesAndDrilldown(conf, 1);
        createSeriesAndDrilldown(conf, 2);

        return chart;
    }

    private void createSeriesAndDrilldown(Configuration conf, int index) {
        DataSeries series = new DataSeries();
        series.setName("Browser brands" + index);

        DataSeriesItem item = new DataSeriesItem("MSIE", 55.11 + index);
        DataSeries drillSeries = new DataSeries("MSIE versions");
        drillSeries.setId("MSIE" + index);
        String[] categories = new String[] { "MSIE 6.0", "MSIE 7.0",
                "MSIE 8.0", "MSIE 9.0" };
        Number[] ys = new Number[] { 10.85 + index, 7.35, 33.06 + index, 2.81 };
        drillSeries.setData(categories, ys);
        series.addItemWithDrilldown(item, drillSeries);

        item = new DataSeriesItem("Firefox", 21.63 + index);
        drillSeries = new DataSeries("Firefox versions");
        drillSeries.setId("Firefox" + index);
        categories = new String[] { "Firefox 2.0", "Firefox 3.0",
                "Firefox 3.5", "Firefox 3.6", "Firefox 4.0" };
        ys = new Number[] { 0.20, 0.83 + index, 1.58, 13.12 + index, 5.43 };
        drillSeries.setData(categories, ys);
        series.addItemWithDrilldown(item, drillSeries);

        item = new DataSeriesItem("Chrome", 11.94 + index);
        drillSeries = new DataSeries("Chrome versions");
        drillSeries.setId("Chrome" + index);
        categories = new String[] { "Chrome 5.0", "Chrome 6.0", "Chrome 7.0",
                "Chrome 8.0", "Chrome 9.0", "Chrome 10.0", "Chrome 11.0",
                "Chrome 12.0" };
        ys = new Number[] { 0.12, 0.19 + index, 0.12, 0.36 + index, 0.32,
                9.91 + index, 0.50, 0.22 + index };
        drillSeries.setData(categories, ys);
        series.addItemWithDrilldown(item, drillSeries);

        item = new DataSeriesItem("Safari", 7.15 + index);
        drillSeries = new DataSeries("Safari versions");
        drillSeries.setId("Safari" + index);
        categories = new String[] { "Safari 5.0", "Safari 4.0",
                "Safari Win 5.0", "Safari 4.1", "Safari/Maxthon", "Safari 3.1",
                "Safari 4.1" };
        ys = new Number[] { 4.55 + index, 1.42 + index, 0.23, 0.21, 0.20,
                0.19 + index, 0.14 + index };
        drillSeries.setData(categories, ys);
        series.addItemWithDrilldown(item, drillSeries);

        item = new DataSeriesItem("Opera", 2.14 + index);
        drillSeries = new DataSeries("Opera versions");
        drillSeries.setId("Opera" + index);
        categories = new String[] { "Opera 9.x", "Opera 10.x", "Opera 11.x" };
        ys = new Number[] { 0.12, 0.37 + index, 1.65 };
        drillSeries.setData(categories, ys);
        series.addItemWithDrilldown(item, drillSeries);
        conf.addSeries(series);
    }

}
