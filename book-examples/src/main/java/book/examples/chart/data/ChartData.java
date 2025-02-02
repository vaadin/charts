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
package book.examples.chart.data;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.DrilldownCallback;
import com.vaadin.addon.charts.DrilldownEvent;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataProviderSeries;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.RangeSeries;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.HorizontalLayout;

public class ChartData {
    public void listSeriesSnippet1(Configuration conf) {
        ListSeries series = new ListSeries("Total Reindeer Population", 181091,
                201485, 188105);
        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setPointStart(1959);
        series.setPlotOptions(plotOptions);
        conf.addSeries(series);
    }

    public void genericDataSeriesSnippet1(Configuration conf) {
        DataSeries series = new DataSeries();
        series.setName("Total Reindeer Population");
        series.add(new DataSeriesItem(1959, 181091));
        series.add(new DataSeriesItem(1960, 201485));
        series.add(new DataSeriesItem(1961, 188105));
        series.add(new DataSeriesItem(1962, 177206));

        // Modify the color of one point
        series.get(2).getMarker().setFillColor(SolidColor.RED);
        conf.addSeries(series);

    }

    public void rangeDataSnippet1(Configuration conf) {
        RangeSeries series = new RangeSeries("Temperature Extremes");

        // Give low-high values in constructor
        series.add(new DataSeriesItem(0, -51.5, 10.9));
        series.add(new DataSeriesItem(1, -49.0, 11.8));

        // Set low-high values with setters
        DataSeriesItem point = new DataSeriesItem();
        point.setX(2);
        point.setLow(-44.3);
        point.setHigh(17.5);
        series.add(point);
    }

    public void rangeSeriesSnippet1(Configuration conf) {
        RangeSeries series = new RangeSeries("Temperature Ranges",
                new Double[] { -51.5, 10.9 }, new Double[] { -49.0, 11.8 },
                // ...
                new Double[] { -47.0, 10.8 });
        conf.addSeries(series);
    }

    public void rangeSeriesSnippet2(Configuration conf, RangeSeries series) {
        series.setRangeData(new Double[][] { new Double[] { -51.5, 10.9 },
                new Double[] { -49.0, 11.8 },
                // ...
                new Double[] { -47.0, 10.8 } });
    }

    // dataProviderSeriesSnippet1
    public class Order {
        private String description;
        private int quantity;
        private double unitPrice;

        public Order(String description, int quantity, double unitPrice) {
            this.description = description;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public String getDescription() {
            return description;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public double getTotalPrice() {
            return unitPrice * quantity;
        }
    }

    public void dataProviderSeriesSnippet2(Configuration conf,
            HorizontalLayout layout) {
        // The data
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("Domain Name", 3, 7.99));
        orders.add(new Order("SSL Certificate", 1, 119.00));
        orders.add(new Order("Web Hosting", 1, 19.95));
        orders.add(new Order("Email Box", 20, 0.15));
        orders.add(new Order("E-Commerce Setup", 1, 25.00));
        orders.add(new Order("Technical Support", 1, 50.00));

        DataProvider<Order, ?> dataProvider = new ListDataProvider<>(orders);
    }

    public void dataProviderSeriesSnippet3(Configuration conf,
            DataProvider<Order, ?> dataProvider) {
        // Create a chart and use the data provider
        Chart chart = new Chart(ChartType.COLUMN);
        Configuration configuration = chart.getConfiguration();
        DataProviderSeries<Order> series = new DataProviderSeries<>(
                dataProvider, Order::getTotalPrice);
        configuration.addSeries(series);
    }

    public void dataProviderSeriesSnippet4(DataProviderSeries<Order> series) {
        series.setName("Order item quantities");
        series.setX(Order::getDescription);
    }

    public void dataProviderSeriesSnippet5(Configuration configuration) {
        // Set correct axis type to show the item name as category
        configuration.getxAxis().setType(AxisType.CATEGORY);
    }

    public void synchronousDrilldown() {
        DataSeries series = new DataSeries();

        DataSeriesItem mainItem = new DataSeriesItem("MSIE", 55.11);

        DataSeries drillDownSeries = new DataSeries("MSIE versions");
        drillDownSeries.setId("MSIE");

        drillDownSeries.add(new DataSeriesItem("MSIE 6.0", 10.85));
        drillDownSeries.add(new DataSeriesItem("MSIE 7.0", 7.35));
        drillDownSeries.add(new DataSeriesItem("MSIE 8.0", 33.06));
        drillDownSeries.add(new DataSeriesItem("MSIE 9.0", 2.81));

        series.addItemWithDrilldown(mainItem, drillDownSeries);
    }

    public void asynchronousDrilldown(Chart chart) {
        DataSeries series = new DataSeries();

        DataSeriesItem mainItem = new DataSeriesItem("MSIE", 55.11);

        series.addItemWithDrilldown(mainItem);

        chart.setDrilldownCallback(new DrilldownCallback() {
            @Override
            public Series handleDrilldown(DrilldownEvent event) {
                DataSeries drillDownSeries = new DataSeries("MSIE versions");

                drillDownSeries.add(new DataSeriesItem("MSIE 6.0", 10.85));
                drillDownSeries.add(new DataSeriesItem("MSIE 7.0", 7.35));
                drillDownSeries.add(new DataSeriesItem("MSIE 8.0", 33.06));
                drillDownSeries.add(new DataSeriesItem("MSIE 9.0", 2.81));

                return drillDownSeries;
            }
        });
    }

    private int[][] reindeerData() {
        // TODO Auto-generated method stub
        return null;
    }

}
