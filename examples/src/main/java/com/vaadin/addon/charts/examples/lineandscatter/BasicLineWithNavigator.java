package com.vaadin.addon.charts.examples.lineandscatter;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Navigator;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;

@SkipFromDemo
public class BasicLineWithNavigator extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Basic Line With Data Labels";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();
        chart.setHeight("450px");
        chart.setWidth("100%");

        Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.LINE);
        configuration.getChart().setMarginRight(130);
        configuration.getChart().setMarginBottom(25);

        configuration.getTitle().setText("Monthly Average Temperature");
        configuration.getSubTitle().setText("Source: WorldClimate.com");

        configuration.getxAxis().setType(AxisType.DATETIME);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setMin(-5d);
        yAxis.setTitle(new AxisTitle("Temperature (°C)"));
        yAxis.getTitle().setAlign(VerticalAlign.MIDDLE);

        DataSeries ds = new DataSeries();
        ds.setName("Tokyo");
        List<Double> values = Arrays.asList(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6);
        Calendar calendar = getCalendar();
        for (Double value : values) {
            DataSeriesItem item = new DataSeriesItem(calendar.getTime(), value);
            ds.add(item);
            calendar.add(Calendar.MONTH,1);
        }
        configuration.addSeries(ds);

        ds = new DataSeries();
        ds.setName("New York");
        values = Arrays.asList(-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5);
        calendar = getCalendar();
        for (Double value : values) {
            DataSeriesItem item = new DataSeriesItem(calendar.getTime(), value);
            ds.add(item);
            calendar.add(Calendar.MONTH,1);
        }
        configuration.addSeries(ds);

        Navigator navigator = configuration.getNavigator();
        navigator.setMargin(75);
        navigator.setEnabled(true);

        chart.drawChart(configuration);
        return chart;
    }

    private Calendar getCalendar() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 12);
        c.set(2016, 0, 1);
        return c;
    }

}
