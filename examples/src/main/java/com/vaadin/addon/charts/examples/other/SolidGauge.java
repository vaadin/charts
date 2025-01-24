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
import com.vaadin.addon.charts.model.Background;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Pane;
import com.vaadin.addon.charts.model.PlotOptionsSolidgauge;
import com.vaadin.addon.charts.model.SeriesTooltip;
import com.vaadin.addon.charts.model.Stop;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

public class SolidGauge extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Solid Gauge";
    }

    @Override
    protected Component getChart() {
        final Chart chart = new Chart();
        chart.setWidth(500, Unit.PIXELS);

        final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.SOLIDGAUGE);

        configuration.getTitle().setText("Speed");

        Pane pane = new Pane();
        pane.setCenter("50%", "85%");
        pane.setSize("140%");
        pane.setStartAngle(-90);
        pane.setEndAngle(90);
        configuration.addPane(pane);

        configuration.getTooltip().setEnabled(false);

        Background bkg = new Background();
        bkg.setBackgroundColor(new SolidColor("#eeeeee"));
        bkg.setInnerRadius("60%");
        bkg.setOuterRadius("100%");
        bkg.setShape("arc");
        bkg.setBorderWidth(0);
        pane.setBackground(bkg);

        YAxis yaxis = configuration.getyAxis();
        yaxis.setLineWidth(0);
        yaxis.setTickInterval(200);
        yaxis.setTickWidth(0);
        yaxis.setMin(0);
        yaxis.setMax(200);
        yaxis.setTitle("");
        yaxis.getTitle().setY(-70);
        yaxis.setLabels(new Labels());
        yaxis.getLabels().setY(16);
        Stop stop1 = new Stop(0.1f, SolidColor.GREEN);
        Stop stop2 = new Stop(0.5f, SolidColor.YELLOW);
        Stop stop3 = new Stop(0.9f, SolidColor.RED);
        yaxis.setStops(stop1, stop2, stop3);

        PlotOptionsSolidgauge plotOptions = new PlotOptionsSolidgauge();
        plotOptions.setTooltip(new SeriesTooltip());
        plotOptions.getTooltip().setValueSuffix(" km/h");
        DataLabels labels = new DataLabels();
        labels.setY(5);
        labels.setBorderWidth(0);
        labels.setUseHTML(true);
        labels.setFormat("<div style=\"text-align:center\"><span style=\"font-size:25px;\">{y}</span><br/>"
                + "                       <span style=\"font-size:12pxg\">km/h</span></div>");
        plotOptions.setDataLabels(labels);
        configuration.setPlotOptions(plotOptions);

        final ListSeries series = new ListSeries("Speed", 80);
        configuration.setSeries(series);

        runWhileAttached(chart, new Runnable() {
            Random r = new Random(0);

            @Override
            public void run() {
                Integer oldValue = series.getData()[0].intValue();
                Integer newValue = (int) (oldValue + (r.nextDouble() - 0.5) * 20.0);
                if (newValue > 200) {
                    newValue = 200;
                } else if (newValue < 0) {
                    newValue = 0;
                }
                series.updatePoint(0, newValue);
            }
        }, 3000, 12000);

        chart.drawChart(configuration);
        return chart;
    }
}
