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
package com.vaadin.addon.charts.examples.other;

import java.util.Calendar;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.Background;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Dial;
import com.vaadin.addon.charts.model.PlotOptionsGauge;
import com.vaadin.addon.charts.model.TickPosition;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.FontWeight;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;

@SkipFromDemo
public class CustomClock extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Gauge clock with a customised dial";
    }

    @Override
    protected Chart getChart() {
        final Chart chart = new Chart();
        chart.setWidth("500px");
        chart.setHeight("200px");

        final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.GAUGE);
        configuration.getChart().setPlotBackgroundColor(null);
        configuration.getChart().setPlotBackgroundImage(null);
        configuration.getChart().setPlotBorderWidth(0);
        configuration.getChart().setPlotShadow(false);
        configuration.setTitle("The Vaadin Charts customised clock");
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

        yAxis.setTitle(new AxisTitle("Powered by<br/>Vaadin Charts 2"));
        yAxis.getTitle().setStyle(new Style());
        yAxis.getTitle().getStyle().setColor(new SolidColor("#BBB"));
        yAxis.getTitle().getStyle().setFontWeight(FontWeight.BOLD);
        yAxis.getTitle().getStyle().setFontSize("8px");
        yAxis.getTitle().getStyle().setLineHeight("10px");
        yAxis.getTitle().setY(10);

        final DataSeries series = new DataSeries();
        final DataSeriesItem second = new DataSeriesItem();

        second.setId("second");
        second.setY(30);
        second.setDial(new Dial());
        second.getDial().setRadius("100%");
        second.getDial().setBaseWidth(1);
        second.getDial().setRearLength("20%");
        second.getDial().setBackgroundColor(SolidColor.AQUA);
        second.getDial().setBorderColor(SolidColor.RED);
        second.getDial().setBorderWidth(10);
        second.getDial().setTopWidth(8);

        series.add(second);

        PlotOptionsGauge plotOptionsGauge = new PlotOptionsGauge();
        plotOptionsGauge.setDataLabels(new DataLabels(false));
        configuration.setPlotOptions(plotOptionsGauge);

        configuration.setSeries(series);

        final Calendar cal = Calendar.getInstance();
        runWhileAttached(chart, new Runnable() {

            @Override
            public void run() {
                cal.setTimeInMillis(System.currentTimeMillis());
                double secs = cal.get(Calendar.SECOND);

                // disable animation when the second dial reaches 0
                boolean animation = secs == 0 ? false : true;
                configuration.getChart().setAnimation(animation);

                second.setY(secs * (12.0 / 60.0));
                series.update(second);
            }
        }, 1000, 15000);

        chart.drawChart(configuration);
        return chart;
    }

}
