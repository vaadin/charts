package com.vaadin.addon.charts.demoandtestapp.lineandscatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.DateDataSeriesItem;
import com.vaadin.addon.charts.model.DateTimeLabelFormats;
import com.vaadin.addon.charts.model.Lang;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class TimeDataWithIrregularIntervalsAndLocalizedTexts extends
        AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Time data with irregular intervals and some localized texts";
    }

    @Override
    protected Component getChart() {
        // localizations
        final Lang fi = new Lang();
        fi.setDecimalPoint(",");
        fi.setShortMonths(new String[] { "Tammi", "Helmi", "Maalis", "Huhti",
                "Touko", "Kesä", "Heinä", "Elo", "Syys", "Loka", "Marras",
                "Joulu" });
        fi.setWeekdays(new String[] { "Ma", "Ti", "Ke", "To", "Pe", "La", "Su" });

        final Lang en = new Lang();
        en.setDecimalPoint(".");
        en.setShortMonths(new String[] { "Jan", "Feb", "Mar", "Apr", "May",
                "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" });
        en.setWeekdays(new String[] { "Sunday", "Monday", "Tuesday",
                "Wednesday", "Thursday", "Friday", "Saturday" });

        final Chart chart = new Chart();
        chart.setHeight("450px");
        chart.setWidth("100%");

        final Configuration configuration = new Configuration();
        configuration.getChart().setType(ChartType.SPLINE);

        configuration.getTitle().setText(
                "Lumen syvyys Vikjafjellet-tunturilla, Norja");
        configuration.getSubTitle().setText("Lokalisointiesimerkki");

        configuration.getTooltip().setFormatter("");

        configuration.getxAxis().setType(AxisType.DATETIME);
        configuration.getxAxis().setDateTimeLabelFormats(
                new DateTimeLabelFormats());
        configuration.getxAxis().getDateTimeLabelFormats().setDay("%a %e %b");

        Axis yAxis = configuration.getyAxis();
        yAxis.setTitle(new Title("Lumen syvyys (m)"));
        yAxis.setMin(0);

        configuration
                .getTooltip()
                .setFormatter(
                        "'<b>'+ this.series.name +'</b><br/>\'+ Highcharts.dateFormat('%e. %b', this.x) +': '+ this.y +' m'");

        DataSeries ls = new DataSeries();
        ls.setPlotOptions(new PlotOptionsSpline());
        ls.setName("Talvi 2007-2008");

        Object[][] data1 = getData1();
        for (int i = 0; i < data1.length; i++) {
            Object[] ds = data1[i];
            DataSeriesItem item = new DateDataSeriesItem((Date) ds[0],
                    (Double) ds[1]);
            ls.addData(item);
        }

        configuration.addSeries(ls);

        ls = new DataSeries();
        ls.setPlotOptions(new PlotOptionsSpline());
        ls.setName("Talvi 2008-2009");

        Object[][] data2 = getData2();
        for (int i = 0; i < data2.length; i++) {
            Object[] ds = data2[i];
            DataSeriesItem item = new DateDataSeriesItem((Date) ds[0],
                    (Double) ds[1]);
            ls.addData(item);
        }
        configuration.addSeries(ls);

        ls = new DataSeries();
        ls.setPlotOptions(new PlotOptionsSpline());
        ls.setName("Talvi 2009-2010");
        Object[][] data3 = getData3();
        for (int i = 0; i < data3.length; i++) {
            Object[] ds = data3[i];
            DataSeriesItem item = new DateDataSeriesItem((Date) ds[0],
                    (Double) ds[1]);
            ls.addData(item);
        }
        configuration.addSeries(ls);
        chart.drawChart(configuration);

        final Button enButton = new Button("English");
        enButton.setId("en-button");
        enButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                ChartOptions.get().setLang(en);
            }
        });

        final Button fiButton = new Button("Finnish");
        fiButton.setId("fi-button");
        fiButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                ChartOptions.get().setLang(fi);
            }
        });

        return new VerticalLayout(new HorizontalLayout(enButton, fiButton),
                chart);
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
        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
