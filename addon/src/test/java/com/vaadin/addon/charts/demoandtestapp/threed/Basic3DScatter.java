package com.vaadin.addon.charts.demoandtestapp.threed;

import java.util.Random;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Frame;
import com.vaadin.addon.charts.model.FramePanel;
import com.vaadin.addon.charts.model.Options3d;
import com.vaadin.addon.charts.model.PlotOptionsScatter;
import com.vaadin.addon.charts.model.ZoomType;
import com.vaadin.addon.charts.model.style.SolidColor;
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
        scatterChart.getConfiguration().getChart().setZoomType(ZoomType.XY);
        scatterChart.getConfiguration().disableCredits();
        scatterChart.getConfiguration().setTitle("Selections as area ranges");
        scatterChart.getConfiguration().setSubTitle(
                "Drag with mouse to draw areas");

        PlotOptionsScatter scatterOptions = new PlotOptionsScatter();
        scatterOptions.setAnimation(false);
        scatterOptions.setPointStart(1);
        DataSeries series = new DataSeries();
        series.setPlotOptions(scatterOptions);
        series.setName("Original");

        Random random = new Random(0);

        for (int i = 0; i < 20; i++) {
            DataSeriesItem dsi = new DataSeriesItem();
            dsi.setY(random.nextInt(10));
            dsi.setX(random.nextInt(10));
            dsi.setZ(random.nextInt(10));
            series.add(dsi);
        }

        scatterChart.getConfiguration().addSeries(series);

        Options3d options3d = new Options3d();
        options3d.setEnabled(true);
        options3d.setAlpha(20);
        options3d.setBeta(30);
        options3d.setDepth(200);
        Frame frame = new Frame();
        frame.setBottom(new FramePanel(SolidColor.GREY, 10));
        options3d.setFrame(frame);
        scatterChart.getConfiguration().getChart().setOptions3d(options3d);

        scatterChart.drawChart();
        return scatterChart;

    }

}