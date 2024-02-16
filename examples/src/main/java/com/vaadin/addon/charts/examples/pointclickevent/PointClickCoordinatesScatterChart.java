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
package com.vaadin.addon.charts.examples.pointclickevent;

import java.util.Random;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.Bottom;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem3d;
import com.vaadin.addon.charts.model.Frame;
import com.vaadin.addon.charts.model.Options3d;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.ZAxis;

@SkipFromDemo
public class PointClickCoordinatesScatterChart
        extends AbstractPointClickCoordinatesChart {

    public PointClickCoordinatesScatterChart() {
        super(ChartType.SCATTER);
    }

    @Override
    protected Chart createChart() {
        Chart chart = super.createChart();

        Configuration conf = chart.getConfiguration();
        XAxis x = conf.getxAxis();
        x.setGridLineWidth(1);
        x.setExtremes(-3, 3);

        if (getCurrentTheme().getxAxis().getGridLineColor() != null) {
            x.setGridLineColor(getCurrentTheme().getxAxis().getGridLineColor());
        }

        YAxis y = conf.getyAxis();
        y.setExtremes(-1, 1);

        ZAxis z = conf.getzAxis();
        z.setMin(-1);
        z.setMax(1);

        Options3d options3d = new Options3d();
        options3d.setEnabled(true);
        options3d.setAlpha(10);
        options3d.setBeta(30);
        options3d.setDepth(80);
        options3d.setViewDistance(40);

        Frame frame = new Frame();
        Bottom bottom = new Bottom();
        bottom.setSize(1);
        frame.setBottom(bottom);
        options3d.setFrame(frame);
        conf.getChart().setOptions3d(options3d);

        chart.drawChart();
        return chart;
    }

    @Override
    protected void addSeries(Configuration conf) {
        DataSeries higherX = new DataSeries();
        higherX.setName("Higher X");
        DataSeries higherY = new DataSeries();
        higherY.setName("Higher Y");
        DataSeries higherZ = new DataSeries();
        higherZ.setName("Higher Z");

        fillSeries(higherX, higherY, higherZ);

        conf.setSeries(higherX, higherY, higherZ);
    }

    private void fillSeries(DataSeries higherX, DataSeries higherY,
            DataSeries higherZ) {
        Random random = new Random(7);
        for (int i = 0; i < 300; i++) {
            double lng = random.nextDouble() * 2 * Math.PI;
            double lat = random.nextDouble() * Math.PI - Math.PI / 2;
            double x = Math.cos(lat) * Math.sin(lng);
            double y = Math.sin(lat);
            double z = Math.cos(lng) * Math.cos(lat);

            DataSeriesItem3d point = new DataSeriesItem3d(x, y, z);
            if (x > y && x > z) {
                higherX.add(point);
            } else if (y > x && y > z) {
                higherY.add(point);
            } else {
                higherZ.add(point);
            }
        }

    }
}
