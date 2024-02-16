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
import com.vaadin.addon.charts.model.DataLabelsRange;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsColumnrange;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class ColumnRange extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Area Range";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.COLUMNRANGE);

        Configuration conf = chart.getConfiguration();
        conf.getChart().setInverted(true);
        conf.setTitle("Temperature variation by month");
        conf.setSubTitle("Observed in Vik i Sogn, Norway, 2009");

        XAxis xAxis = new XAxis();
        xAxis.setCategories("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
                "Aug", "Sep", "Oct", "Nov", "Dec");
        conf.addxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setTitle("Temperature ( °C )");
        conf.addyAxis(yAxis);

        Tooltip tooltip = new Tooltip();
        tooltip.setValueSuffix("°C");
        conf.setTooltip(tooltip);

        PlotOptionsColumnrange columnRange = new PlotOptionsColumnrange();
        columnRange.setDataLabels(new DataLabelsRange(true));
        columnRange.getDataLabels().setFormatter(
                "function() {return this.y + '°C';}");
        conf.setPlotOptions(columnRange);

        conf.getLegend().setEnabled(false);

        // RangeSeries has some helper constructors of which example below, but
        // here we use the raw DataSeries API
        // RangeSeries data = new RangeSeries("Temperatures", getRawData());
        DataSeries data = new DataSeries();
        data.setName("Temperatures");
        for (Number[] minMaxPair : getRawData()) {
            DataSeriesItem item = new DataSeriesItem();
            item.setLow(minMaxPair[0]);
            item.setHigh(minMaxPair[1]);
            data.add(item);
        }

        conf.setSeries(data);

        return chart;
    }

    private Number[][] getRawData() {
        return new Number[][] { { -9.7, 9.4 }, { -8.7, 6.5 }, { -3.5, 9.4 },
                { -1.4, 19.9 }, { 0.0, 22.6 }, { 2.9, 29.5 }, { 9.2, 30.7 },
                { 7.3, 26.5 }, { 4.4, 18.0 }, { -3.1, 11.4 }, { -5.2, 10.4 },
                { -13.5, 9.8 } };
    }
}
