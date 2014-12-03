package com.vaadin.addon.charts.demoandtestapp.lineandscatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.DateTimeLabelFormats;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.Title;
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

        Axis yAxis = configuration.getyAxis();
        yAxis.setTitle(new Title("Snow depth (m)"));
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
            DataSeriesItem item = new DataSeriesItem((Date) ds[0],
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
            DataSeriesItem item = new DataSeriesItem((Date) ds[0],
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
            DataSeriesItem item = new DataSeriesItem((Date) ds[0],
                    (Double) ds[1]);
            ls.add(item);
        }
        configuration.addSeries(ls);
        chart.drawChart(configuration);
        return chart;
    }

    private Object[][] getData3() {
        return new Object[][] { { d("1970,9,9"), 0d },
                { d("1970,9,14"), 0.15 }, { d("1970,10,28"), 0.35 },
                { d("1970,11,12"), 0.46 }, { d("1971,0,1"), 0.59 },
                { d("1971,0,24"), 0.58 }, { d("1971,1,1"), 0.62 },
                { d("1971,1,7"), 0.65 }, { d("1971,1,23"), 0.77 },
                { d("1971,2,8"), 0.77 }, { d("1971,2,14"), 0.79 },
                { d("1971,2,24"), 0.86 }, { d("1971,3,4"), 0.8 },
                { d("1971,3,18"), 0.94 }, { d("1971,3,24"), 0.9 },
                { d("1971,4,16"), 0.39 }, { d("1971,4,21"), 0d } };
    }

    private Object[][] getData2() {
        return new Object[][] { { d("1970,9,18"), 0d },
                { d("1970,9,26"), 0.2 }, { d("1970,11,1"), 0.47 },
                { d("1970,11,11"), 0.55 }, { d("1970,11,25"), 1.38 },
                { d("1971,0,8"), 1.38 }, { d("1971,0,15"), 1.38 },
                { d("1971,1,1"), 1.38 }, { d("1971,1,8"), 1.48 },
                { d("1971,1,21"), 1.5 }, { d("1971,2,12"), 1.89 },
                { d("1971,2,25"), 2.0 }, { d("1971,3,4"), 1.94 },
                { d("1971,3,9"), 1.91 }, { d("1971,3,13"), 1.75 },
                { d("1971,3,19"), 1.6 }, { d("1971,4,25"), 0.6 },
                { d("1971,4,31"), 0.35 }, { d("1971,5,7"), 0d } };
    }

    private Object[][] getData1() {
        return new Object[][] { { d("1970,9,27"), 0d },
                { d("1970,10,10"), 0.6 }, { d("1970,10,18"), 0.7 },
                { d("1970,11,2"), 0.8 }, { d("1970,11,9"), 0.6 },
                { d("1970,11,16"), 0.6 }, { d("1970,11,28"), 0.67 },
                { d("1971,0,1"), 0.81 }, { d("1971,0,8"), 0.78 },
                { d("1971,0,12"), 0.98 }, { d("1971,0,27"), 1.84 },
                { d("1971,1,10"), 1.80 }, { d("1971,1,18"), 1.80 },
                { d("1971,1,24"), 1.92 }, { d("1971,2,4"), 2.49 },
                { d("1971,2,11"), 2.79 }, { d("1971,2,15"), 2.73 },
                { d("1971,2,25"), 2.61 }, { d("1971,3,2"), 2.76 },
                { d("1971,3,6"), 2.82 }, { d("1971,3,13"), 2.8 },
                { d("1971,4,3"), 2.1 }, { d("1971,4,26"), 1.1 },
                { d("1971,5,9"), 0.25 }, { d("1971,5,12"), 0d } };
    }

    private final DateFormat df = new SimpleDateFormat("yyyy,MM,dd");

    /**
     * Helper method to convert Date string YYYY,MM,dd to Date
     * 
     * @param dateString
     * @return
     */
    private Date d(String dateString) {
        df.setTimeZone(TimeZone.getTimeZone("EET"));
        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
