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
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Shape;
import com.vaadin.addon.charts.model.Stacking;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
@SkipFromDemo
public class ColumnWithShapedLabels extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Stacked Column with circular labels";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.COLUMN);

        Configuration conf = chart.getConfiguration();

        conf.setTitle(new Title("Stacked column chart"));

        XAxis xAxis = new XAxis();
        xAxis.setCategories(new String[] { "Apples", "Oranges", "Pears",
                "Grapes", "Bananas" });
        conf.addxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setMin(0);
        yAxis.setTitle(new AxisTitle("Total fruit consumption"));
        conf.addyAxis(yAxis);

        PlotOptionsColumn plotOptions = new PlotOptionsColumn();
        plotOptions.setStacking(Stacking.NORMAL);
        DataLabels labels = new DataLabels(true);
        labels.setBackgroundColor(SolidColor.BURLYWOOD);
        labels.setColor(new SolidColor("white"));
        labels.setShape(Shape.DIAMOND);

        Style style=new Style();
        style.setTextShadow("0 0 3px black");
        labels.setStyle(style);

        plotOptions.setDataLabels(labels);
        conf.setPlotOptions(plotOptions);

        conf.addSeries(new ListSeries("John", new Number[] { 5, 3, 4, 7, 2 }));
        conf.addSeries(new ListSeries("Jane", new Number[] { 2, 2, 3, 2, 1 }));
        conf.addSeries(new ListSeries("Joe", new Number[] { 3, 4, 4, 2, 5 }));

        chart.drawChart(conf);
        return chart;
    }
}
