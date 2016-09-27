package com.vaadin.addon.charts.examples.container;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

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
        Date date;
        Integer value;

        public TestItem(Date date, Integer value) {
            this.date = date;
            this.value = value;
        }

        public Date getDate() {
            return date;
        }

        public Integer getValue() {
            return value;
        }
    }
    @Override
    public String getDescription() {
        return "Simple Chart with ContainerSeries";
    }

    @Override
    protected Component getChart() {
        // Create container with two points
        Collection<TestItem> col = new ArrayList<>();
        DataSource<TestItem> ds = new ListDataSource<>(col);
        Calendar cal = Calendar.getInstance();
        cal.set(2013, 2, 22, 12, 00);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        col.add(new TestItem(cal.getTime(),5));
        cal.add(Calendar.DATE, 1);
        col.add(new TestItem(cal.getTime(),10));
        cal.add(Calendar.DATE, 1);
        col.add(new TestItem(cal.getTime(),5));

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
