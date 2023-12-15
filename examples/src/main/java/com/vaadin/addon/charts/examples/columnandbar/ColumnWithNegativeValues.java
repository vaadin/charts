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
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Credits;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class ColumnWithNegativeValues extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Column with negative values";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.COLUMN);

        Configuration conf = chart.getConfiguration();
        conf.setTitle(new Title("Column chart with negative values"));

        PlotOptionsColumn column = new PlotOptionsColumn();
        column.setMinPointLength(3);
        conf.setPlotOptions(column);

        XAxis xAxis = new XAxis();
        xAxis.setCategories("Apples", "Oranges", "Pears", "Grapes", "Bananas");
        conf.addxAxis(xAxis);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("function() { return ''+ this.series.name +': '+ this.y +'';}");
        conf.setTooltip(tooltip);

        conf.setCredits(new Credits(false));

        conf.addSeries(new ListSeries("John", 5, 0.1, 4, 7, 2));
        conf.addSeries(new ListSeries("Jane", 2, -2, -0.1, 2, 1));
        conf.addSeries(new ListSeries("Joe", 3, 4, 4, -2, 5));

        chart.drawChart(conf);

        return chart;
    }

}
