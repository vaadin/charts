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
package com.vaadin.addon.charts.examples.other;

import java.util.Random;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsGauge;
import com.vaadin.addon.charts.model.SeriesTooltip;
import com.vaadin.addon.charts.model.TickPosition;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

public class GaugeWithDualAxes extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Gauge with dual axes";
    }

    @Override
    protected Component getChart() {
        final Chart chart = new Chart();
        chart.setWidth("500px");

        final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.GAUGE);
        configuration.getChart().setAlignTicks(false);
        configuration.getChart().setPlotBackgroundColor(null);
        configuration.getChart().setPlotBackgroundImage(null);
        configuration.getChart().setPlotBorderWidth(0);
        configuration.getChart().setPlotShadow(false);
        configuration.setTitle("Speedometer with dual axes");

        configuration.getPane().setStartAngle(-150);
        configuration.getPane().setEndAngle(150);

        YAxis yAxis = new YAxis();
        yAxis.setMin(0);
        yAxis.setMax(200);
        yAxis.setLineColor(new SolidColor("#339"));
        yAxis.setTickColor(new SolidColor("#339"));
        yAxis.setMinorTickColor(new SolidColor("#339"));
        yAxis.setOffset(-25);
        yAxis.setLineWidth(2);
        Labels labels = new Labels();
        labels.setDistance(-20);
        labels.setRotationPerpendicular();
        labels.setRotation("auto");
        yAxis.setLabels(labels);

        yAxis.setTickLength(5);
        yAxis.setMinorTickLength(5);
        yAxis.setEndOnTick(false);

        YAxis yAxis2 = new YAxis();
        yAxis2.setMin(0);
        yAxis2.setMax(124);

        yAxis2.setLineColor(new SolidColor("#933"));
        yAxis2.setTickColor(new SolidColor("#933"));
        yAxis2.setMinorTickColor(new SolidColor("#933"));
        yAxis2.setOffset(-20);
        yAxis2.setLineWidth(2);
        labels = new Labels();
        labels.setDistance(12);
        labels.setRotationPerpendicular();
        yAxis2.setLabels(labels);
        yAxis2.setTickLength(5);
        yAxis2.setMinorTickLength(5);
        yAxis2.setEndOnTick(false);
        yAxis2.setTickPosition(TickPosition.OUTSIDE);
        yAxis2.setMinorTickPosition(TickPosition.OUTSIDE);

        configuration.addyAxis(yAxis);
        configuration.addyAxis(yAxis2);

        final ListSeries series = new ListSeries("Speed", 80);

        PlotOptionsGauge plotOptionsGauge = new PlotOptionsGauge();
        plotOptionsGauge.setDataLabels(new DataLabels());
        plotOptionsGauge
                .getDataLabels()
                .setFormatter(
                        "function() {return '<span style=\"color:#339\">'+ this.y + ' km/h</span><br/>' + '<span style=\"color:#933\">' + Math.round(this.y * 0.621) + ' mph</span>';}");
        GradientColor gradient = GradientColor.createLinear(0, 0, 0, 1);
        gradient.addColorStop(0, new SolidColor("#DDD"));
        gradient.addColorStop(1, new SolidColor("#FFF"));
        plotOptionsGauge.getDataLabels().setBackgroundColor(gradient);
        plotOptionsGauge.setTooltip(new SeriesTooltip());
        plotOptionsGauge.getTooltip().setValueSuffix(" km/h");
        series.setPlotOptions(plotOptionsGauge);

        configuration.setSeries(series);

        runWhileAttached(chart, new Runnable() {

            Random r = new Random(0);

            @Override
            public void run() {
                Integer oldValue = series.getData()[0].intValue();
                Integer newValue = (int) (oldValue + (r.nextDouble() - 0.5) * 20.0);
                series.updatePoint(0, newValue);
            }
        }, 5000, 12000);

        chart.drawChart(configuration);

        return chart;
    }
}
