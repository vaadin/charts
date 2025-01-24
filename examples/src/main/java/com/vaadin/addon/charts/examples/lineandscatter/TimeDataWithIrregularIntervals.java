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
package com.vaadin.addon.charts.examples.lineandscatter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.DateTimeLabelFormats;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;

public class TimeDataWithIrregularIntervals extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Time data with irregular intervals";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();
        chart.setHeight("450px");
        chart.setWidth("100%");

        Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.SPLINE);

        configuration.getTitle().setText(
                "Snow depth in the Vikjafjellet mountain, Norway");
        configuration.getSubTitle().setText(
                "An example of irregular time data in Highcharts JS");

        configuration.getTooltip().setFormatter("");

        configuration.getxAxis().setType(AxisType.DATETIME);
        configuration.getxAxis().setDateTimeLabelFormats(
                new DateTimeLabelFormats("%e. %b", "%b"));

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle(new AxisTitle("Snow depth (m)"));
        yAxis.setMin(0);

        configuration
                .getTooltip()
                .setFormatter(
                        "'<b>'+ this.series.name +'</b><br/>\'+ Highcharts.dateFormat('%e. %b', this.x) +': '+ this.y +' m'");

        DataSeries ls = new DataSeries();
        ls.setPlotOptions(new PlotOptionsSpline());
        ls.setName("Winter 2007-2008");

        Object[][] data1 = getData1();
        for (int i = 0; i < data1.length; i++) {
            Object[] ds = data1[i];
            DataSeriesItem item = new DataSeriesItem((Instant) ds[0],
                    (Double) ds[1]);
            ls.add(item);
        }

        configuration.addSeries(ls);

        ls = new DataSeries();
        ls.setPlotOptions(new PlotOptionsSpline());
        ls.setName("Winter 2008-2009");

        Object[][] data2 = getData2();
        for (int i = 0; i < data2.length; i++) {
            Object[] ds = data2[i];
            DataSeriesItem item = new DataSeriesItem((Instant) ds[0],
                    (Double) ds[1]);
            ls.add(item);
        }

        configuration.addSeries(ls);

        ls = new DataSeries();
        ls.setPlotOptions(new PlotOptionsSpline());
        ls.setName("Winter 2009-2010");
        Object[][] data3 = getData3();
        for (int i = 0; i < data3.length; i++) {
            Object[] ds = data3[i];
            DataSeriesItem item = new DataSeriesItem((Instant) ds[0],
                    (Double) ds[1]);
            ls.add(item);
        }
        configuration.addSeries(ls);
        chart.drawChart(configuration);
        return chart;
    }

    private Object[][] getData3() {
        return new Object[][] { { d("1970,10,09"), 0d },
                { d("1970,10,14"), 0.15 }, { d("1970,11,28"), 0.35 },
                { d("1970,12,12"), 0.46 }, { d("1971,01,01"), 0.59 },
                { d("1971,01,24"), 0.58 }, { d("1971,02,01"), 0.62 },
                { d("1971,02,07"), 0.65 }, { d("1971,02,23"), 0.77 },
                { d("1971,03,08"), 0.77 }, { d("1971,03,14"), 0.79 },
                { d("1971,03,24"), 0.86 }, { d("1971,04,04"), 0.8 },
                { d("1971,04,18"), 0.94 }, { d("1971,04,24"), 0.9 },
                { d("1971,05,16"), 0.39 }, { d("1971,05,21"), 0d } };
    }

    private Object[][] getData2() {
        return new Object[][] { { d("1970,10,18"), 0d },
                { d("1970,10,26"), 0.2 }, { d("1970,12,01"), 0.47 },
                { d("1970,12,11"), 0.55 }, { d("1970,12,25"), 1.38 },
                { d("1971,01,08"), 1.38 }, { d("1971,01,15"), 1.38 },
                { d("1971,02,01"), 1.38 }, { d("1971,02,08"), 1.48 },
                { d("1971,02,21"), 1.5 }, { d("1971,03,12"), 1.89 },
                { d("1971,03,25"), 2.0 }, { d("1971,04,04"), 1.94 },
                { d("1971,04,09"), 1.91 }, { d("1971,04,13"), 1.75 },
                { d("1971,04,19"), 1.6 }, { d("1971,05,25"), 0.6 },
                { d("1971,05,31"), 0.35 }, { d("1971,06,07"), 0d } };
    }

    private Object[][] getData1() {
        return new Object[][] { { d("1970,10,27"), 0d },
                { d("1970,11,10"), 0.6 }, { d("1970,11,18"), 0.7 },
                { d("1970,12,02"), 0.8 }, { d("1970,12,09"), 0.6 },
                { d("1970,12,16"), 0.6 }, { d("1970,12,28"), 0.67 },
                { d("1971,01,01"), 0.81 }, { d("1971,01,08"), 0.78 },
                { d("1971,01,12"), 0.98 }, { d("1971,01,27"), 1.84 },
                { d("1971,02,10"), 1.80 }, { d("1971,02,18"), 1.80 },
                { d("1971,02,24"), 1.92 }, { d("1971,03,04"), 2.49 },
                { d("1971,03,11"), 2.79 }, { d("1971,03,15"), 2.73 },
                { d("1971,03,25"), 2.61 }, { d("1971,04,02"), 2.76 },
                { d("1971,04,06"), 2.82 }, { d("1971,04,13"), 2.8 },
                { d("1971,05,03"), 2.1 }, { d("1971,05,26"), 1.1 },
                { d("1971,06,09"), 0.25 }, { d("1971,06,12"), 0d } };
    }

    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy,MM,dd");

    /**
     * Helper method to convert Date string YYYY,MM,dd to Date
     *
     * @param dateString
     * @return
     */
    /**
     * Helper method to convert Date string YYYY,MM,dd to Date
     *
     * @param stringFormat
     * @return
     */
    private Instant d(String stringFormat) {
        LocalDate date;
        try {
            date = LocalDate.parse(stringFormat, df);
        } catch (DateTimeParseException e) {
            throw new RuntimeException(e);
        }
        return date.atStartOfDay().toInstant(ZoneOffset.UTC);
    }
}
