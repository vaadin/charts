package com.vaadin.addon.charts.examples.container;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 *
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 *
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.ExampleUtil;
import com.vaadin.addon.charts.model.AbstractSeries;
import com.vaadin.addon.charts.model.ChartDataSeries;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.server.data.DataSource;
import com.vaadin.server.data.ListDataSource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;

public class ChartWithExternalContainer extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Chart with Container containing much data";
    }

    @Override
    protected Component getChart() {
        HorizontalLayout lo = new HorizontalLayout();
        lo.setSpacing(true);
        DataSource<Order> ds = getOrderDataSource();

        ChartDataSeries chartDataSeries1 = createChartDataSeries1(ds);
        ChartDataSeries chartDataSeries2 = createChartDataSeries2(ds);
        Component table = createGrid(ds);
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

    private ChartDataSeries createChartDataSeries1(DataSource<Order> dataSource) {
        ChartDataSeries<Order> chartDS = new ChartDataSeries(dataSource);
        chartDS.setName("Order item quantities");
        chartDS.setPlotOptions(new PlotOptionsPie());
        chartDS.setYValueProvider(Order::getQuantity);
        chartDS.setNameProvider(Order::getDescription);
        return chartDS;
    }

    private ChartDataSeries createChartDataSeries2(DataSource<Order> dataSource) {
        ChartDataSeries<Order> chartDS = new ChartDataSeries(dataSource);
        chartDS.setName("Order item prices");
        chartDS.setPlotOptions(new PlotOptionsColumn());

        chartDS.setYValueProvider(order -> order.getItemPrice().intValue());
        chartDS.setNameProvider(Order::getDescription);
        return chartDS;
    }

    private Component createGrid(DataSource<Order> dataSource) {
        Grid<Order> grid = new Grid();
        grid.setCaption("Data from Vaadin Container");
        grid.setDataSource(dataSource);
        grid.addColumn("description",Order::getDescription);
        grid.addColumn("quantity",order -> Integer.toString(order.getQuantity().intValue()));
        grid.addColumn(ExampleUtil.ORDER_ITEMPRICE_PROPERTY_ID.toString(),order -> Integer.toString(order.getItemPrice().intValue()));
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

    public DataSource getOrderDataSource() {
        Collection<Order> orders = new ArrayList<>();
        orders.add(new Order("Domain Name", 3, 7.99));
        orders.add(new Order("SSL Certificate", 1, 119.00));
        orders.add(new Order("Web Hosting", 1, 19.95));
        orders.add(new Order("Email Box", 20, 0.15));
        orders.add(new Order("E-Commerce Setup", 1, 25.00));
        orders.add(new Order("Technical Support", 1, 50.00));

        return new ListDataSource<>(orders);
    }

}
