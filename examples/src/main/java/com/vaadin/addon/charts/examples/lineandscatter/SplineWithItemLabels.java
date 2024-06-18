/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.examples.lineandscatter;

import static com.vaadin.addon.charts.model.Shape.CIRCLE;
import static com.vaadin.addon.charts.model.VerticalAlign.MIDDLE;
import static com.vaadin.addon.charts.model.style.FontWeight.BOLD;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.ui.Component;

@SkipFromDemo
public class SplineWithItemLabels extends AbstractVaadinChartExample {

    private final int ONE_HOUR = 60 * 60 * 1000;
    private final DateFormat df = new SimpleDateFormat("yyyy,MM,dd");

    @Override
    public String getDescription() {
        return "Spline With Labels in last item";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.SPLINE);

        Configuration configuration = chart.getConfiguration();

        configuration.getTitle().setText("Wind speed during two days");
        configuration
                .getSubTitle()
                .setText(
                        "October 6th and 7th 2009 at two locations in Vik i Sogn, Norway");

        configuration.getxAxis().setType(AxisType.DATETIME);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle(new AxisTitle("Wind speed (m/s)"));

        PlotOptionsSpline plotOptions = new PlotOptionsSpline();
        configuration.setPlotOptions(plotOptions);
        plotOptions.setMarker(new Marker(false));
        plotOptions.setPointInterval(ONE_HOUR);
        plotOptions.setPointStart(toDate("2009,9,6"));
        DataSeries ds = new DataSeries();
        ds.setName("Hestavollane");
        ds.setData(4.3, 5.1, 4.3, 5.2, 5.4, 4.7, 3.5, 4.1, 5.6, 7.4, 6.9, 7.1,
                7.9, 7.9, 7.5, 6.7, 7.7, 7.7, 7.4, 7.0, 7.1, 5.8, 5.9, 7.4,
                8.2, 8.5, 9.4, 8.1, 10.9, 10.4, 10.9, 12.4, 12.1, 9.5, 7.5,
                7.1, 7.5, 8.1, 6.8, 3.4, 2.1, 1.9, 2.8, 2.9, 1.3, 4.4, 4.2,
                3.0, 3.0);
        DataSeriesItem item = new DataSeriesItem();
        item.setY(4.51);
        item.setDataLabels(getDataLabels());
        ds.add(item);

        configuration.addSeries(ds);

        ds = new DataSeries();
        ds.setName("Voll");
        ds.setData(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1, 0.0, 0.3, 0.0,
                0.0, 0.4, 0.0, 0.1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.6, 1.2, 1.7, 0.7, 2.9, 4.1, 2.6, 3.7, 3.9, 1.7, 2.3,
                3.0, 3.3, 4.8, 5.0, 4.8, 5.0, 3.2, 2.0, 0.9, 0.4, 0.3, 0.5, 0.4);
        item = new DataSeriesItem();
        item.setY(0.27);
        item.setDataLabels(getDataLabels());
        ds.add(item);
        configuration.addSeries(ds);

        return chart;
    }

    private DataLabels getDataLabels() {
        DataLabels dataLabels = new DataLabels(true);
        dataLabels.setStyle(new Style());
        dataLabels.getStyle().setFontWeight(BOLD);
        dataLabels.setY(-20);
        dataLabels.setShape(CIRCLE);
        dataLabels.setBackgroundColor(SolidColor.BLUEVIOLET);
        dataLabels.setColor(SolidColor.WHITESMOKE);
        dataLabels.setVerticalAlign(MIDDLE);
        return dataLabels;
    }

    /**
     * Helper method to convert Date string YYYY,MM,dd to Date
     *
     * @param dateString
     * @return
     */
    private Date toDate(String dateString) {
        df.setTimeZone(TimeZone.getTimeZone("EET"));
        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
