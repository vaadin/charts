package com.vaadin.addon.charts.examples.threed;

import java.util.Random;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Bottom;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem3d;
import com.vaadin.addon.charts.model.Frame;
import com.vaadin.addon.charts.model.Options3d;
import com.vaadin.addon.charts.model.PlotOptionsScatter;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.ZAxis;
import com.vaadin.ui.Component;

public class Basic3DScatter extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "3D Scatter";
    }

    @Override
    protected Component getChart() {
        return createScatterChart();
    }

    private Chart createScatterChart() {
        final Chart scatterChart = new Chart(ChartType.SCATTER);
        scatterChart.setId("chart");
        scatterChart.getConfiguration().disableCredits();
        scatterChart.getConfiguration().setTitle("Points of a sphere");
        PlotOptionsScatter scatterOptions = new PlotOptionsScatter();
        scatterOptions.setAnimation(false);
        scatterOptions.setPointStart(1);

        DataSeries higherX = new DataSeries();
        higherX.setName("Higher X");
        DataSeries higherY = new DataSeries();
        higherY.setName("Higher Y");
        DataSeries higherZ = new DataSeries();
        higherZ.setName("Higher Z");

        fillSeries(higherX, higherY, higherZ);

        scatterChart.getConfiguration().addSeries(higherX);
        scatterChart.getConfiguration().addSeries(higherY);
        scatterChart.getConfiguration().addSeries(higherZ);

        XAxis x = scatterChart.getConfiguration().getxAxis();
        x.setGridLineWidth(1);
        x.setMin(-3);
        x.setMax(3);
        // FIXME missing generated API
        x.setExtremes(-3, 3);

        if (getCurrentTheme().getxAxis().getGridLineColor() != null) {
            x.setGridLineColor(getCurrentTheme().getxAxis().getGridLineColor());
        }

        YAxis y = scatterChart.getConfiguration().getyAxis();
        y.setExtremes(-1, 1);
        y.setMin(-1);
        y.setMax(1);

        ZAxis z = scatterChart.getConfiguration().getzAxis();
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
        scatterChart.getConfiguration().getChart().setOptions3d(options3d);

        scatterChart.drawChart();
        return scatterChart;

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
