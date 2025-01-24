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
package com.vaadin.addon.charts.examples.dataprovider;

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.AbstractSeries;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataProviderSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;

public class ChartWithExternalDataProvider extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Chart with DataProvider containing much data";
    }

    @Override
    protected Component getChart() {
        HorizontalLayout lo = new HorizontalLayout();
        lo.setSpacing(true);
        DataProvider<Order, ?> dp = getOrderDataProvider();

        DataProviderSeries<Order> chartDataSeries1 = createChartDataSeries1(dp);
        DataProviderSeries<Order> chartDataSeries2 = createChartDataSeries2(dp);
        Component table = createGrid(dp);
        table.setSizeFull();
        Chart chart1 = createChart1(chartDataSeries1);
        chart1.setSizeFull();
        Chart chart2 = createChart2(chartDataSeries2);
        chart2.setSizeFull();

        lo.setWidth("100%");
        lo.setHeight("450px");
        lo.addComponents(table);
        lo.addComponent(chart1);
        lo.addComponent(chart2);
        lo.setExpandRatio(table, 0.2f);
        lo.setExpandRatio(chart1, 0.4f);
        lo.setExpandRatio(chart2, 0.4f);
        return lo;
    }

    private DataProviderSeries<Order> createChartDataSeries1(
            DataProvider<Order, ?> dataProvider) {
        DataProviderSeries<Order> chartDS = new DataProviderSeries<>(dataProvider);
        chartDS.setName("Order item quantities");
        chartDS.setPlotOptions(new PlotOptionsPie());
        chartDS.setY(Order::getQuantity);
        chartDS.setPointName(Order::getDescription);
        return chartDS;
    }

    private DataProviderSeries<Order> createChartDataSeries2(
            DataProvider<Order, ?> dataProvider) {
        DataProviderSeries<Order> chartDS = new DataProviderSeries<>(dataProvider);
        chartDS.setName("Order item prices");
        chartDS.setPlotOptions(new PlotOptionsColumn());

        chartDS.setY(order -> order.getItemPrice().intValue());
        chartDS.setPointName(Order::getDescription);
        return chartDS;
    }

    private Component createGrid(DataProvider<Order, ?> dataProvider) {
        Grid<Order> grid = new Grid<>();
        grid.setCaption("Data from Vaadin DataProvider");
        grid.setDataProvider(dataProvider);
        grid.addColumn(Order::getDescription).setCaption("Description");
        grid.addColumn(
                order -> Integer.toString(order.getQuantity().intValue()))
                .setCaption("Quantity");
        grid.addColumn(
                order -> Integer.toString(order.getItemPrice().intValue()))
                .setCaption("Item Price");
        return grid;
    }

    public static Chart createChart(Series container,ChartType type,String text) {
        final Chart chart = new Chart();

        final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(type);
        configuration.getTitle().setText(text);
        configuration.getLegend().setEnabled(false);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        configuration.setPlotOptions(plotOptions);

        configuration.setSeries(container);
        chart.drawChart(configuration);
        return chart;
    }
    public static Chart createChart1(Series container) {
        final Chart chart = createChart(container,ChartType.PIE,("Order item quantities"));
        return chart;
    }

    public static Chart createChart2(AbstractSeries container) {
        final Chart chart = createChart(container,ChartType.COLUMN,("Order item totals"));
        YAxis ax = new YAxis();
        ax.setTitle("");
        chart.getConfiguration().addyAxis(ax);
        return chart;
    }


    private class Order  {
        private String description;
        private Number quantity;
        private Number unitPrice;

        public Order(String description, Number quantity, Number unitPrice) {
            this.description = description;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public String getDescription() {
            return description;
        }

        public Number getQuantity() {
            return quantity;
        }

        public Number getUnitPrice() {
            return unitPrice;
        }
        public Number getItemPrice() {
            return unitPrice.doubleValue() * quantity.doubleValue();
        }
    }

    public ListDataProvider<Order> getOrderDataProvider() {
        Collection<Order> orders = new ArrayList<>();
        orders.add(new Order("Domain Name", 3, 7.99));
        orders.add(new Order("SSL Certificate", 1, 119.00));
        orders.add(new Order("Web Hosting", 1, 19.95));
        orders.add(new Order("Email Box", 20, 0.15));
        orders.add(new Order("E-Commerce Setup", 1, 25.00));
        orders.add(new Order("Technical Support", 1, 50.00));

        return new ListDataProvider<>(orders);
    }

}
