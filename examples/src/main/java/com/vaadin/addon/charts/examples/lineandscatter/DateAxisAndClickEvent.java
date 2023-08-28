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
package com.vaadin.addon.charts.examples.lineandscatter;

import java.util.Calendar;
import java.util.Random;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartClickEvent;
import com.vaadin.addon.charts.ChartClickListener;
import com.vaadin.addon.charts.PointClickEvent;
import com.vaadin.addon.charts.PointClickListener;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.util.Util;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

public class DateAxisAndClickEvent extends AbstractVaadinChartExample {

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
        configuration.setTitle("Date axis and click events");
        configuration.getChart().setType(ChartType.SPLINE);

        configuration.getxAxis().setType(AxisType.DATETIME);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 12);
        c.set(2013, 2, 11);

        DataSeries dataSeries = new DataSeries();
        Number[] values = new Number[] { 71.5, 29.9, 106.4 };
        Random r = new Random(0);
        for (Number number : values) {
            c.add(Calendar.MINUTE, r.nextInt(5));
            DataSeriesItem item = new DataSeriesItem(c.getTime(), number);
            dataSeries.add(item);
        }
        configuration.addSeries(dataSeries);
        chart.drawChart(configuration);

        chart.addChartClickListener(new ChartClickListener() {

            @Override
            public void onClick(ChartClickEvent event) {
                /*
                 * The axis value is in client side library's raw format: unix
                 * timestamp, "shifted" to UTC time zone
                 */;
                double timeStampShiftedToUc = event.getxAxisValue();
                /*
                 * When working with Date objects, developers probably want to
                 * convert it to Date object at their local time zone.
                 */
                Notification.show("Clicked @ "
                        + Util.toServerInstant(timeStampShiftedToUc));
            }
        });

        chart.addPointClickListener(new PointClickListener() {

            @Override
            public void onClick(PointClickEvent event) {
                /*
                 * Same with point clicks...
                 */;
                double timeStampShiftedToUc = event.getX();
                Notification.show("Clicked Point with Date value "
                        + Util.toServerInstant(timeStampShiftedToUc));
            }
        });

        return chart;
    }

}
