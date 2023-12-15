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
package com.vaadin.addon.charts.examples.declarative;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.declarative.ChartComponentMapper;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Component;
import com.vaadin.ui.declarative.Design;

@DesignRoot(value = "basic_line.html")
public class DeclarativeBasicLine extends AbstractVaadinChartExample {

    Chart myChart;

    @Override
    public String getDescription() {
        return "Basic line chart using declarative format";
    }

    @Override
    protected void setup() {
        Design.setComponentMapper(new ChartComponentMapper());
        Design.read(this);
        ListSeries ls = new ListSeries();
        ls.setName("Tokyo");
        ls.setData(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3,
                13.9, 9.6);
        myChart.getConfiguration().addSeries(ls);
        ls = new ListSeries();
        ls.setName("New York");
        ls.setData(-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1,
                8.6, 2.5);
        myChart.getConfiguration().addSeries(ls);
        ls = new ListSeries();
        ls.setName("Berlin");
        ls.setData(-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9,
                1.0);
        myChart.getConfiguration().addSeries(ls);
        ls = new ListSeries();
        ls.setName("London");
        ls.setData(3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6,
                4.8);
        myChart.getConfiguration().addSeries(ls);
    }

    @Override
    protected Component getChart() {
        return null;
    }

}
