package com.vaadin.addon.charts.demoandtestapp.threed;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem3d;
import com.vaadin.addon.charts.model.Frame;
import com.vaadin.addon.charts.model.FramePanel;
import com.vaadin.addon.charts.model.Options3d;
import com.vaadin.addon.charts.model.PlotOptionsScatter;
import com.vaadin.addon.charts.model.XAxis;
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
        scatterChart.getConfiguration().disableCredits();
        scatterChart.getConfiguration().setTitle("Selections as area ranges");

        PlotOptionsScatter scatterOptions = new PlotOptionsScatter();
        scatterOptions.setAnimation(false);
        scatterOptions.setPointStart(1);
        DataSeries series = new DataSeries();
        series.setPlotOptions(scatterOptions);
        series.setName("Original");
        fillSeries(series);

        scatterChart.getConfiguration().addSeries(series);
        XAxis x = scatterChart.getConfiguration().getxAxis();
        x.setGridLineWidth(1);
        if (getCurrentTheme().getxAxis().getGridLineColor() != null) {
            x.setGridLineColor((SolidColor) getCurrentTheme().getxAxis()
                    .getGridLineColor());
        }

        Options3d options3d = new Options3d();
        options3d.setEnabled(true);
        options3d.setAlpha(20);
        options3d.setBeta(30);
        options3d.setDepth(200);

        Frame frame = new Frame();
        frame.setBottom(new FramePanel(null, 1));
        options3d.setFrame(frame);
        scatterChart.getConfiguration().getChart().setOptions3d(options3d);

        scatterChart.drawChart();
        return scatterChart;

    }

    private void fillSeries(DataSeries series) {
        series.add(new DataSeriesItem3d(1, 1, 1));
        series.add(new DataSeriesItem3d(1, 1, 2));
        series.add(new DataSeriesItem3d(1, 1, 5));
        series.add(new DataSeriesItem3d(2, 3, 2));
        series.add(new DataSeriesItem3d(2, 6, 4));
        series.add(new DataSeriesItem3d(4, 5, 7));
        series.add(new DataSeriesItem3d(4, 2, 8));
        series.add(new DataSeriesItem3d(7, 1, 3));
        series.add(new DataSeriesItem3d(7, 1, 5));
        series.add(new DataSeriesItem3d(8, 1, 5));
    }

}