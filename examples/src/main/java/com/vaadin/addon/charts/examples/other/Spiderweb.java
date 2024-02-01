/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.examples.other;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Pane;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PointPlacement;
import com.vaadin.addon.charts.model.TickmarkPlacement;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class Spiderweb extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Spiderweb";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.LINE);

        Configuration conf = chart.getConfiguration();
        conf.getChart().setPolar(true);
        conf.setTitle("Budget vs spending");
        conf.getTitle().setX(-80);

        Pane pane = new Pane();
        pane.setSize(80, Unit.PERCENTAGE);
        conf.addPane(pane);

        XAxis axis = new XAxis();
        axis.setCategories("Sales", "Marketing", "Development",
                "Customer Support", "Information Technology", "Administration");
        axis.setTickmarkPlacement(TickmarkPlacement.ON);
        axis.setLineWidth(0);

        YAxis yaxs = new YAxis();
        yaxs.setGridLineInterpolation("polygon");
        yaxs.setMin(0);
        yaxs.setLineWidth(0);
        conf.addxAxis(axis);
        conf.addyAxis(yaxs);

        conf.getTooltip().setShared(true);
        conf.getTooltip().setValuePrefix("$");

        conf.getLegend().setAlign(HorizontalAlign.RIGHT);
        conf.getLegend().setVerticalAlign(VerticalAlign.TOP);
        conf.getLegend().setY(100);
        conf.getLegend().setLayout(LayoutDirection.VERTICAL);

        ListSeries line1 = new ListSeries(43000, 19000, 60000, 35000, 17000,
                10000);
        ListSeries line2 = new ListSeries(50000, 39000, 42000, 31000, 26000,
                14000);
        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setPointPlacement(PointPlacement.ON);
        line1.setPlotOptions(plotOptions);
        line1.setName("Allocated Budget");

        plotOptions = new PlotOptionsLine();
        plotOptions.setPointPlacement(PointPlacement.ON);
        line2.setPlotOptions(plotOptions);
        line2.setName("Actual Spending");

        conf.setSeries(line1, line2);

        chart.drawChart(conf);

        return chart;
    }
}
