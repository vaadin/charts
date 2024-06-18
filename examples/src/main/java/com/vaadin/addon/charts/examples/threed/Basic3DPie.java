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
package com.vaadin.addon.charts.examples.threed;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.PointClickEvent;
import com.vaadin.addon.charts.PointClickListener;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Options3d;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

@SuppressWarnings("serial")
public class Basic3DPie extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "3D Pie chart";
    }

    @Override
    protected Component getChart() {
        Component ret = createChart();
        ret.setWidth("100%");
        ret.setHeight("450px");
        return ret;
    }

    public static Chart createChart() {
        Chart chart = new Chart(ChartType.PIE);

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Browser market shares at a specific website, 2010");

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setCursor(Cursor.POINTER);
        DataLabels dataLabels = new DataLabels(true);
        dataLabels
                .setFormatter("'<b>'+ this.point.name +'</b>: '+ this.percentage +' %'");
        plotOptions.setDataLabels(dataLabels);

        plotOptions.setDepth(45);

        conf.setPlotOptions(plotOptions);

        final DataSeries series = new DataSeries();
        series.add(new DataSeriesItem("Firefox", 45.0));
        series.add(new DataSeriesItem("IE", 26.8));
        DataSeriesItem chrome = new DataSeriesItem("Chrome", 12.8);
        chrome.setSliced(true);
        chrome.setSelected(true);
        series.add(chrome);
        series.add(new DataSeriesItem("Safari", 8.5));
        series.add(new DataSeriesItem("Opera", 6.2));
        series.add(new DataSeriesItem("Others", 0.7));
        conf.setSeries(series);

        Options3d options3d = new Options3d();
        options3d.setEnabled(true);
        options3d.setAlpha(60);
        conf.getChart().setOptions3d(options3d);

        chart.addPointClickListener(new PointClickListener() {

            @Override
            public void onClick(PointClickEvent event) {
                Notification.show("Click: "
                        + series.get(event.getPointIndex()).getName());
            }
        });

        chart.drawChart(conf);

        return chart;
    }

}
