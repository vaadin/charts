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
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;

@SkipFromDemo
public class PartialUpdateConflicts extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Test in which partial updates do not mess the data. Use ?debug mode!";
    }

    private final Integer[] data = new Integer[] { 1, 2, 3 };

    @Override
    protected Component getChart() {
        final Chart chart = new Chart();
        chart.setHeight("200px");

        final ListSeries listSeries = new ListSeries(data);

        chart.getConfiguration().addSeries(listSeries);

        Button button = new Button("Do stuff", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                listSeries.addData(100, true, true);
                ArrayUtils.reverse(data);
                chart.getConfiguration().setSeries(new ListSeries(data));
                chart.drawChart();
            }
        });
        button.setId("b1");

        final Chart chart2 = new Chart();
        chart2.setHeight("200px");

        final DataSeries ds = new DataSeries();
        int i = 0;
        for (Integer value : data) {
            ds.add(new DataSeriesItem(i++, value));
        }

        chart2.getConfiguration().addSeries(ds);

        Button button2 = new Button("Do stuff DataSeries",
                new Button.ClickListener() {

                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        final DataSeriesItem dataSeriesItem = new DataSeriesItem(
                                ds.size(), new Random().nextInt(10));
                        ds.add(dataSeriesItem);
                        ArrayUtils.reverse(data);
                        chart2.getConfiguration().setSeries(
                                new ListSeries(data));
                        chart2.drawChart();
                    }
                });
        button2.setId("b2");

        return new VerticalLayout(chart, button, chart2, button2);
    }

}
