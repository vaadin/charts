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
package com.vaadin.addon.charts.examples.other;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Background;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Pane;
import com.vaadin.addon.charts.model.PlotOptionsArea;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PlotOptionsSeries;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class PolarChart extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Polar Chart";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();

        Configuration conf = chart.getConfiguration();
        conf.getChart().setPolar(true);
        conf.setTitle("Polar Chart");

        Pane pane = new Pane(0, 360);
        conf.addPane(pane);
        pane.setBackground(new Background[] {});

        XAxis axis = new XAxis();
        axis.setTickInterval(45);
        axis.setMin(0);
        axis.setMax(360);
        Labels labels = new Labels();
        labels.setFormatter("function() {return this.value + '°';}");
        axis.setLabels(labels);
        YAxis yaxs = new YAxis();
        yaxs.setMin(0);
        conf.addxAxis(axis);
        conf.addyAxis(yaxs);

        PlotOptionsSeries series = new PlotOptionsSeries();
        PlotOptionsColumn column = new PlotOptionsColumn();
        series.setPointStart(0);
        series.setPointInterval(45);
        column.setPointPadding(0);
        column.setGroupPadding(0);

        conf.setPlotOptions(series, column);

        ListSeries col = new ListSeries(8, 7, 6, 5, 4, 3, 2, 1);
        ListSeries line = new ListSeries(1, 2, 3, 4, 5, 6, 7, 8);
        ListSeries area = new ListSeries(1, 8, 2, 7, 3, 6, 4, 5);

        col.setPlotOptions(new PlotOptionsColumn());
        col.setName(ChartType.COLUMN.toString());
        line.setPlotOptions(new PlotOptionsLine());
        line.setName(ChartType.LINE.toString());
        area.setPlotOptions(new PlotOptionsArea());
        area.setName(ChartType.AREA.toString());

        conf.setSeries(col, line, area);
        chart.drawChart(conf);
        return chart;
    }
}
