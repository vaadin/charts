package com.vaadin.addon.charts.demoandtestapp.dynamic;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartClickEvent;
import com.vaadin.addon.charts.ChartClickListener;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class Events extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Tests click event processing on the server side. Clicking on chart adds new point to series at clicked y value. ";
    }

    @Override
    protected Component getChart() {
        final Chart chart = new Chart();
        chart.setHeight("450px");
        chart.setWidth("100%");

        final Configuration configuration = chart.getConfiguration();
        configuration.setTitle("Click to add point");

        configuration.getChart().setType(ChartType.SPLINE);

        final ListSeries series = new ListSeries(29.9, 71.5, 106.4, 129.2,
                144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4);
        configuration.setSeries(series);

        chart.drawChart(configuration);

        chart.addChartClickListener(new ChartClickListener() {
            @Override
            public void onClick(ChartClickEvent event) {
                double getyAxisValue = event.getyAxisValue();
                series.addData(getyAxisValue);
            }
        });

        return chart;
    }

}
