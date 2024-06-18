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
package com.vaadin.addon.charts.examples.other;

import java.util.Random;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Background;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.Dial;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Pane;
import com.vaadin.addon.charts.model.PlotBand;
import com.vaadin.addon.charts.model.PlotOptionsGauge;
import com.vaadin.addon.charts.model.TickPosition;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

public class VUMeter extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "VU Meter";
    }

    @Override
    protected Component getChart() {
        final Chart chart = new Chart();
        chart.setWidth("600px");
        chart.setHeight("200px");

        GradientColor gradient = GradientColor.createLinear(0, 0, 0, 1);
        gradient.addColorStop(0, new SolidColor("#FFF4C6"));
        gradient.addColorStop(0.3, new SolidColor("#FFFFFF"));
        gradient.addColorStop(1, new SolidColor("#FFF4C6"));

        final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.GAUGE);
        configuration.getChart().setPlotBackgroundColor(gradient);
        configuration.getChart().setPlotBackgroundImage(null);
        configuration.getChart().setPlotBorderWidth(1);
        configuration.setTitle("VU meter");

        Pane pane1 = new Pane(-45, 45);
        Pane pane2 = new Pane(-45, 45);
        pane1.setBackground(new Background[] {});
        pane2.setBackground(new Background[] {});
        pane1.setCenter("25%", "145%");
        pane2.setCenter("75%", "145%");
        pane1.setSize("300px");
        pane2.setSize("300");

        configuration.addPane(pane1);
        configuration.addPane(pane2);

        PlotBand plotBand1 = new PlotBand(0, 6, new SolidColor("#C02316"));
        plotBand1.setInnerRadius("100%");
        plotBand1.setOuterRadius("105%");

        PlotBand plotBand2 = new PlotBand(0, 6, new SolidColor("#C02316"));
        plotBand2.setInnerRadius("100%");
        plotBand2.setOuterRadius("105%");

        YAxis yAxis = new YAxis();
        yAxis.setPane(pane1);
        yAxis.setTitle("VU<br/><span style=\"font-size:8px\">Channel A</span>");
        yAxis.getTitle().setY(-40);
        yAxis.setMin(-20);
        yAxis.setMax(6);
        yAxis.setTickPosition(TickPosition.OUTSIDE);
        yAxis.setMinorTickPosition(TickPosition.OUTSIDE);
        Labels labels = new Labels();
        labels.setDistance(20);
        labels.setRotationPerpendicular();
        yAxis.setLabels(labels);
        yAxis.setPlotBands(plotBand1);

        YAxis yAxis2 = new YAxis();
        yAxis2.setPane(pane2);
        yAxis2.setTitle("VU<br/><span style=\"font-size:8px\">Channel B</span>");
        yAxis2.getTitle().setY(-40);
        yAxis2.setMin(-20);
        yAxis2.setMax(6);
        yAxis2.setTickPosition(TickPosition.OUTSIDE);
        yAxis2.setMinorTickPosition(TickPosition.OUTSIDE);
        labels = new Labels();
        labels.setDistance(20);
        labels.setRotationPerpendicular();
        yAxis2.setLabels(labels);
        yAxis2.setPlotBands(plotBand2);

        configuration.addyAxis(yAxis);
        configuration.addyAxis(yAxis2);

        PlotOptionsGauge gauge = new PlotOptionsGauge();
        gauge.setDataLabels(new DataLabels(false));
        gauge.setDial(new Dial());
        gauge.getDial().setRadius("100%");

        configuration.setPlotOptions(gauge);

        final ListSeries series1 = new ListSeries(-20);
        final ListSeries series2 = new ListSeries(-20);
        series1.setyAxis(0);
        series2.setyAxis(1);
        configuration.setSeries(series1, series2);

        runWhileAttached(chart, new Runnable() {

            final Random r = new Random(0);

            @Override
            public void run() {
                double left = series1.getData()[0].doubleValue();
                double inc = (r.nextDouble() - 0.5) * 3;
                double leftVal = left + inc;
                double rightVal = leftVal + inc / 3;
                if (leftVal < -20 || leftVal > 6) {
                    leftVal = left - inc;
                }
                if (rightVal < -20 || rightVal > 6) {
                    rightVal = leftVal;
                }

                series1.updatePoint(0, leftVal);
                series2.updatePoint(0, rightVal);
            }
        }, 500, 12000);

        chart.drawChart(configuration);
        return chart;
    }
}
