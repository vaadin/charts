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
package com.vaadin.addon.charts.examples.dataprovider;

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataProviderSeries;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;

public class SimpleChartWithDataProviderSeries extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Simple Chart with ContainerSeries";
    }

    private DataProvider<Browser, ?> browsers = new ListDataProvider<>(
            getMockData());

    @Override
    protected Component getChart() {
        HorizontalLayout lo = new HorizontalLayout();
        Component table = createGrid();
        DataProviderSeries<Browser> ds = createChartDS();
        Component chart = createChart(ds);

        table.setSizeFull();
        chart.setSizeFull();

        lo.setWidth("100%");
        lo.setHeight("450px");
        lo.addComponents(table);
        lo.addComponent(chart);
        return lo;
    }

    private Component createGrid() {

        Grid<Browser> grid = new Grid<>();
        grid.setDataProvider(browsers);
        grid.addColumn(Browser::getName).setCaption("Name");
        grid.addColumn(
                browser -> Double.toString(browser.getShare().doubleValue()))
                .setCaption("Y");
        grid.addColumn(browser -> browser.getColor().toString())
                .setCaption("Color");
        grid.setCaption("Data from Vaadin DataProvider");
        return grid;
    }

    private DataProviderSeries<Browser> createChartDS() {
        DataProviderSeries<Browser> ds = new DataProviderSeries<>(browsers);
        ds.setPointName(Browser::getName);
        ds.setY(Browser::getShare);
        ds.setProperty("color", Browser::getColor);
        ds.setName("Browser share");

        return ds;
    }

    public static Chart createChart(Series ds) {
        final Chart chart = new Chart();

        final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.PIE);
        configuration.getTitle().setText("Data from Vaadin DataProvider");

        configuration.setSeries(ds);

        chart.drawChart(configuration);

        return chart;
    }

    private Collection<Browser> getMockData() {
        Collection<Browser> browsers = new ArrayList<>();
        String[] names = new String[] { "MSIE", "Firefox", "Chrome", "Safari",
                "Opera" };
        Number[] values = new Number[] { 55.11, 21.63, 11.94, 7.15, 2.14 };
        Color[] colors = getThemeColors();
        for (int i = 0; i < names.length; i++) {
            browsers.add(new Browser(names[i], values[i], colors[i]));

        }
        return browsers;
    }

    private class Browser {
        private String name;
        private Number share;
        private Color color;

        public Browser(String name, Number share, Color color) {
            this.name = name;
            this.share = share;
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public Number getShare() {
            return share;
        }

        public Color getColor() {
            return color;
        }

    }
}
