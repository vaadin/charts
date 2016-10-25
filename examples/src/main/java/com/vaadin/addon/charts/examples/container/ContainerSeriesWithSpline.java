package com.vaadin.addon.charts.examples.container;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartDataSeries;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.server.data.DataSource;
import com.vaadin.server.data.ListDataSource;
import com.vaadin.ui.Component;

@SkipFromDemo
public class ContainerSeriesWithSpline extends AbstractVaadinChartExample {

    private class TestItem {
        Instant date;
        Integer value;

        public TestItem(Instant date, Integer value) {
            this.date = date;
            this.value = value;
        }

        public Instant getDate() {
            return date;
        }

        public Integer getValue() {
            return value;
        }
    }
    @Override
    public String getDescription() {
        return "Simple Chart with ChartDataSeries";
    }

    @Override
    protected Component getChart() {
        // Create container with two points
        Collection<TestItem> col = new ArrayList<>();
        DataSource<TestItem> ds = new ListDataSource<>(col);
        LocalDateTime dateTime = LocalDateTime.of(2013, 3, 22, 12, 00);

        col.add(new TestItem(dateTime.toInstant(ZoneOffset.UTC), 5));
        dateTime = dateTime.plusDays(1);
        col.add(new TestItem(dateTime.toInstant(ZoneOffset.UTC), 10));
        dateTime = dateTime.plusDays(1);
        col.add(new TestItem(dateTime.toInstant(ZoneOffset.UTC), 5));

        ChartDataSeries<TestItem> chartDataSeries = new ChartDataSeries(ds);
        chartDataSeries.setXValueProvider(TestItem::getDate);
        chartDataSeries.setYValueProvider(TestItem::getValue);


        // Create chart and render
        Chart chart = new Chart();

        // Create chart config
        Configuration config = chart.getConfiguration();

        config.getxAxis().setType(AxisType.DATETIME);

        // Wrap container in a container data series

        chartDataSeries.setPlotOptions(new PlotOptionsSpline());

        // Add points to series
        config.addSeries(chartDataSeries);

        chart.setSizeFull();
        chart.drawChart(config);

        return chart;
    }
}
