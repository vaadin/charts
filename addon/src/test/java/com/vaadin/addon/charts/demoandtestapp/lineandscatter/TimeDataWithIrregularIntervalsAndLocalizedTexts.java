package com.vaadin.addon.charts.demoandtestapp.lineandscatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.SkipFromDemo;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.DateDataSeriesItem;
import com.vaadin.addon.charts.model.DateTimeLabelFormats;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.Lang;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SkipFromDemo
@SuppressWarnings({ "serial" })
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
        fi.setMonths(new String[] { "Tammikuu", "Helmikuu", "Maaliskuu",
                "Huhtikuu", "Toukokuu", "Kesäkuu", "Heinäkuu", "Elokuu",
                "Syyskuu", "Lokakuu", "Marraskuu", "Joulukuu" });
        fi.setWeekdays(new String[] { "Ma", "Ti", "Ke", "To", "Pe", "La", "Su" });

        final Lang en = new Lang();
        en.setDecimalPoint(".");
        en.setShortMonths(new String[] { "Jan", "Feb", "Mar", "Apr", "May",
                "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" });
        en.setMonths(new String[] { "January", "February", "March", "April",
                "May", "June", "Jule", "August", "September", "October",
                "November", "December" });
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
        // configuration.getxAxis().getDateTimeLabelFormats().setMonth("%B");

        Axis yAxis = configuration.getyAxis();
        yAxis.setTitle(new Title("Lumen syvyys (m)"));
        yAxis.setMin(0);

        DataSeries ls = new DataSeries();
        ls.setPlotOptions(new PlotOptionsSpline());
        ls.getPlotOptions().setDataLabels(new Labels(true));
        ls.getPlotOptions().setEnableMouseTracking(false);
        ls.getPlotOptions()
                .getDataLabels()
                .setFormatter(
                        "return Highcharts.dateFormat('%a %d %B',this.x);");

        ls.setName("Talvi 2009-2010");
        Object[][] data3 = getData3();
        for (int i = 0; i < data3.length; i++) {
            Object[] ds = data3[i];
            DataSeriesItem item = new DateDataSeriesItem((Date) ds[0],
                    (Double) ds[1]);
            ls.add(item);
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
                { d("1971,1,7"), 0.65 }, { d("1971,1,23"), 0.77 } };
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
