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
package book.examples.chart.data;

import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.DrilldownCallback;
import com.vaadin.addon.charts.DrilldownEvent;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ContainerDataSeries;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.RangeSeries;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;

public class ChartData {
    public void listSeriesSnippet1(Configuration conf) {
        ListSeries series = new ListSeries("Total Reindeer Population", 181091,
                201485, 188105);
        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setPointStart(1959);
        series.setPlotOptions(plotOptions);
        conf.addSeries(series);
    }

    public void listSeriesSnippet2(Configuration conf) {
        // Original representation
        int data[][] = reindeerData();

        // Create a list series with X values starting from 1959
        ListSeries series = new ListSeries("Reindeer Population");
        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setPointStart(1959);
        series.setPlotOptions(plotOptions);

        // Add the data points
        for (int row[] : data) {
            series.addData(row[1]);
        }

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

    public void containerDataSeriesSnippet1(Configuration conf,
            HorizontalLayout layout) {
        // The data
        BeanItemContainer<Planet> container = new BeanItemContainer<Planet>(
                Planet.class);
        container.addBean(new Planet("Mercury", 4900));
        container.addBean(new Planet("Venus", 12100));
        container.addBean(new Planet("Earth", 12800));
        // ...

        // Display it in a table
        Table table = new Table("Planets", container);
        table.setPageLength(container.size());
        table.setVisibleColumns("name", "diameter");
        layout.addComponent(table);

        // Display it in a chart
        Chart chart = new Chart(ChartType.COLUMN);
        // ... Configure it ...

        // Wrap the container in a data series
        ContainerDataSeries series = new ContainerDataSeries(container);

        // Set up the name and Y properties
        series.setNamePropertyId("name");
        series.setYPropertyId("diameter");

        conf.addSeries(series);
    }

    public void containerDataSeriesSnippet2(Configuration conf,
            BeanItemContainer<Planet> container) {
        // Set the category labels on the axis correspondingly
        XAxis xaxis = new XAxis();
        String names[] = new String[container.size()];
        List<Planet> planets = container.getItemIds();
        for (int i = 0; i < planets.size(); i++) {
            names[i] = planets.get(i).getName();
        }
        xaxis.setCategories(names);
        xaxis.setTitle("Planet");
        conf.addxAxis(xaxis);
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

    private class Planet {

        public Planet(String string, int i) {
            // TODO Auto-generated constructor stub
        }

        public String getName() {
            // TODO Auto-generated method stub
            return null;
        }

    }
}
