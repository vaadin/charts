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
package com.vaadin.addon.charts.examples.pie;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
@SkipFromDemo
public class PieWithCustomBorder extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Pie with gradient fill";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.PIE);

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Browser market shares at a specific website, 2010");

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setBorderColor(new SolidColor("green"));
        plotOptions.setBorderWidth(6);
        plotOptions.setCursor(Cursor.POINTER);
        DataLabels dataLabels = new DataLabels(true);
        dataLabels.setColor(SolidColor.BLACK);
        dataLabels.setConnectorColor(SolidColor.BLACK);
        dataLabels
                .setFormatter("'<b>'+ this.point.name +'</b>: '+ this.percentage +' %'");
        plotOptions.setDataLabels(dataLabels);
        conf.setPlotOptions(plotOptions);

        conf.setSeries(getBrowserMarketShareSeries());

        chart.drawChart();
        return chart;
    }

    private DataSeries getBrowserMarketShareSeries() {
        DataSeriesItem firefox = new DataSeriesItem("Firefox", 45.0);
        firefox.setColor(createRadialGradient(new SolidColor(255, 128, 0),
                new SolidColor(128, 64, 0)));

        DataSeriesItem ie = new DataSeriesItem("IE", 26.8);
        ie.setColor(createRadialGradient(new SolidColor(0, 255, 255),
                new SolidColor(0, 128, 128)));

        DataSeriesItem chrome = new DataSeriesItem("Chrome", 12.8);
        chrome.setColor(createRadialGradient(new SolidColor(255, 255, 0),
                new SolidColor(128, 128, 0)));
        chrome.setSliced(true);
        chrome.setSelected(true);

        DataSeriesItem safari = new DataSeriesItem("Safari", 8.5);
        safari.setColor(createRadialGradient(new SolidColor(0, 128, 255),
                new SolidColor(0, 64, 128)));

        DataSeriesItem opera = new DataSeriesItem("Opera", 6.2);
        opera.setColor(createRadialGradient(new SolidColor(255, 0, 0),
                new SolidColor(128, 0, 0)));

        DataSeriesItem others = new DataSeriesItem("Others", 0.7);
        others.setColor(createRadialGradient(new SolidColor(0, 128, 0),
                new SolidColor(0, 64, 0)));

        return new DataSeries(firefox, ie, chrome, safari, opera, others);
    }

    /**
     * Creates a radial gradient with the specified start and end colors.
     */
    private GradientColor createRadialGradient(SolidColor start, SolidColor end) {
        GradientColor color = GradientColor.createRadial(0.5, 0.3, 0.7);
        color.addColorStop(0, start);
        color.addColorStop(1, end);
        return color;
    }

}
