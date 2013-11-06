package com.vaadin.addon.charts.demoandtestapp.other;

import java.util.Calendar;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Background;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Dial;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.PlotOptionsGauge;
import com.vaadin.addon.charts.model.TickPosition;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.FontWeight;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.ui.Component;

public class Clock extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Clock";
    }

    @Override
    protected Component getChart() {
        final Chart chart = new Chart();
        chart.setWidth("500px");
        chart.setHeight("200px");

        final Configuration configuration = new Configuration();
        configuration.getChart().setType(ChartType.GAUGE);
        configuration.getChart().setPlotBackgroundColor(null);
        configuration.getChart().setPlotBackgroundImage(null);
        configuration.getChart().setPlotBorderWidth(0);
        configuration.getChart().setPlotShadow(false);
        configuration.setTitle("The Vaadin Charts clock");
        configuration.getCredits().setEnabled(false);

        GradientColor gradient1 = GradientColor.createRadial(0.5, -0.4, 1.9);
        gradient1.addColorStop(0.5, new SolidColor(255, 255, 255, 0.2));
        gradient1.addColorStop(0.5, new SolidColor(200, 200, 200, 0.2));

        Background[] background = new Background[2];
        background[0] = new Background();

        background[1] = new Background();
        background[1].setBackgroundColor(gradient1);
        background[1].setBorderWidth(1);
        background[1].setOuterRadius("107%");

        configuration.getPane().setBackground(background);

        YAxis yAxis = configuration.getyAxis();
        yAxis.getLabels().setDistance(-20);

        yAxis.setMin(0);
        yAxis.setMax(12);
        yAxis.setLineWidth(0);
        yAxis.setShowFirstLabel(false);
        yAxis.setMinorTickInterval("auto");
        yAxis.setMinorTickWidth(1);
        yAxis.setMinorTickLength(5);
        yAxis.setMinorTickPosition(TickPosition.INSIDE);
        yAxis.setMinorGridLineWidth(0);
        yAxis.setMinorTickColor(new SolidColor("#666"));
        yAxis.setTickInterval(1);
        yAxis.setTickWidth(2);
        yAxis.setTickPosition(TickPosition.INSIDE);
        yAxis.setTickLength(10);
        yAxis.setTickColor(new SolidColor("#666"));

        yAxis.setTitle(new Title("Powered by<br/>Vaadin Charts"));
        yAxis.getTitle().setStyle(new Style());
        yAxis.getTitle().getStyle().setColor(new SolidColor("#BBB"));
        yAxis.getTitle().getStyle().setFontWeight(FontWeight.BOLD);
        yAxis.getTitle().getStyle().setFontSize("8px");
        yAxis.getTitle().getStyle().setLineHeight("10px");
        yAxis.getTitle().setY(10);

        final DataSeries series = new DataSeries();
        final DataSeriesItem hour = new DataSeriesItem();
        final DataSeriesItem minute = new DataSeriesItem();
        final DataSeriesItem second = new DataSeriesItem();

        hour.setId("hour");
        hour.setY(10);
        hour.setDial(new Dial());
        hour.getDial().setRadius("60%");
        hour.getDial().setBaseWidth(4);
        hour.getDial().setRearLength("0%");
        hour.getDial().setBaseLength("95%");

        minute.setId("minute");
        minute.setY(10);
        minute.setDial(new Dial());
        minute.getDial().setBaseLength("95%");
        minute.getDial().setRearLength("0%");

        second.setId("second");
        second.setY(30);
        second.setDial(new Dial());
        second.getDial().setRadius("100%");
        second.getDial().setBaseWidth(1);
        second.getDial().setRearLength("20%");

        series.add(hour);
        series.add(minute);
        series.add(second);

        PlotOptionsGauge plotOptionsGauge = new PlotOptionsGauge();
        plotOptionsGauge.setDataLabels(new Labels(false));
        configuration.setPlotOptions(plotOptionsGauge);

        configuration.setSeries(series);

        final Calendar cal = Calendar.getInstance();
        runWhileAttached(chart, new Runnable() {

            @Override
            public void run() {
                cal.setTimeInMillis(System.currentTimeMillis());
                double hours = cal.get(Calendar.HOUR);
                double mins = cal.get(Calendar.MINUTE);
                double secs = cal.get(Calendar.SECOND);

                // disable animation when the second dial reaches 0
                boolean animation = secs == 0 ? false : true;
                configuration.getChart().setAnimation(animation);

                hour.setY(hours + (mins / 60.0));
                minute.setY(mins * (12.0 / 60.0) + secs * (12.0 / 3600.0));
                second.setY(secs * (12.0 / 60.0));
                series.update(hour);
                series.update(minute);
                series.update(second);
            }
        }, 1000, 12000);

        chart.drawChart(configuration);
        return chart;
    }
}
