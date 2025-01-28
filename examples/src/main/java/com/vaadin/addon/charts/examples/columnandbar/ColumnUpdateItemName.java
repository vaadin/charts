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
package com.vaadin.addon.charts.examples.columnandbar;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
@SkipFromDemo
public class ColumnUpdateItemName extends AbstractVaadinChartExample {

    private DataSeriesItem item;
    private DataSeries series;

    @Override
    public String getDescription() {
        return "Basic column with one item that gets renamed via button click";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.COLUMN);
        chart.setId("column-update-item-name");
        Configuration conf = chart.getConfiguration();
        conf.getxAxis().setType(AxisType.CATEGORY);

        item = new DataSeriesItem(0, 6);
        item.setName("X");
        series = new DataSeries(item);
        conf.setSeries(series);

        return chart;
    }

    @Override
    protected void setup() {
        super.setup();

        Button button = new Button("update item name", e -> {
            item.setName("Y");
            series.update(item);
        });
        button.setId("update-button");
        addComponent(button);
    }
}
