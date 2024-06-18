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
package com.vaadin.addon.charts.examples.container;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ContainerDataSeries;
import com.vaadin.addon.charts.model.DataLabelsRange;
import com.vaadin.addon.charts.model.PlotOptionsColumnrange;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Component;

@SkipFromDemo
public class ContainerSeriesWithHighAndLow extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Ranged with ContainerSeries high and low set";
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

        IndexedContainer vaadinContainer = new IndexedContainer();
        vaadinContainer.addContainerProperty("min", Number.class, null);
        vaadinContainer.addContainerProperty("max", Number.class, null);
        for (Number[] minMaxPair : getRawData()) {
            Item item = vaadinContainer.getItem(vaadinContainer.addItem());
            item.getItemProperty("min").setValue(minMaxPair[0]);
            item.getItemProperty("max").setValue(minMaxPair[1]);
        }

        ContainerDataSeries data = new ContainerDataSeries(vaadinContainer);
        data.setLowPropertyId("min");
        data.setHighPropertyId("max");
        data.setName("Temperatures");

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
