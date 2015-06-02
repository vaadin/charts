package com.vaadin.addon.charts.examples.lineandscatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.IntervalUnit;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsSeries;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

@SkipFromDemo
public class TimeSeriesIntervalUnit extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Time Series Zoomable";
    }

    @Override
    protected Component getChart() {
        final Chart chart = new Chart();
        chart.setHeight("450px");
        chart.setWidth("100%");

        Configuration configuration = chart.getConfiguration();
        configuration.getChart().setSpacingRight(20);

        configuration.getTitle().setText("Point interval test");

        configuration.getxAxis().setType(AxisType.DATETIME);

        final PlotOptionsSeries plotOptions = new PlotOptionsSeries();
        try {
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            plotOptions.setPointStart(df.parse("20150101"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        plotOptions.setPointInterval(1);
        plotOptions.setPointIntervalUnit(IntervalUnit.MONTH);

        configuration.setPlotOptions(plotOptions);
        ListSeries ls = new ListSeries();
        ls.setData(29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4,
                194.1, 95.6, 54.4);
        configuration.setSeries(ls);

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(chart);
        layout.addComponent(new Button("one day interval",
                new Button.ClickListener() {

                    @Override
                    public void buttonClick(ClickEvent event) {
                        plotOptions.setPointInterval(24 * 3600 * 1000);
                        plotOptions.setPointIntervalUnit(null);
                        chart.drawChart();
                    }
                }));
        return layout;

    }
}
