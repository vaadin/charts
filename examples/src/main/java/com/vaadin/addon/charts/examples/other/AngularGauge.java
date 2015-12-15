package com.vaadin.addon.charts.examples.other;

import java.util.Random;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.Background;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotBand;
import com.vaadin.addon.charts.model.PlotOptionsGauge;
import com.vaadin.addon.charts.model.SeriesTooltip;
import com.vaadin.addon.charts.model.TickPosition;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

public class AngularGauge extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Angular Gauge";
    }

    @Override
    protected Component getChart() {
        final Chart chart = new Chart();
        chart.setWidth("500px");

        final Configuration configuration = chart.getConfiguration();
        // FIXME remove toString() once enums are used in model (CHARTS-159)
        configuration.getChart().setType(ChartType.GAUGE.toString());
        configuration.getChart().setPlotBackgroundColor(null);
        configuration.getChart().setPlotBackgroundImage(null);
        configuration.getChart().setPlotBorderWidth(0);
        configuration.getChart().setPlotShadow(false);
        configuration.setTitle("Speedometer");

        GradientColor gradient1 = GradientColor.createLinear(0, 0, 0, 1);
        gradient1.addColorStop(0, new SolidColor("#FFF"));
        gradient1.addColorStop(1, new SolidColor("#333"));

        GradientColor gradient2 = GradientColor.createLinear(0, 0, 0, 1);
        gradient2.addColorStop(0, new SolidColor("#333"));
        gradient2.addColorStop(1, new SolidColor("#FFF"));

        Background[] background = new Background[3];
        background[0] = new Background();
        background[0].setBackgroundColor(gradient1);
        background[0].setBorderWidth(0);
        background[0].setOuterRadius("109%");

        background[1] = new Background();
        background[1].setBackgroundColor(gradient2);
        background[1].setBorderWidth(1);
        background[1].setOuterRadius("107%");

        background[2] = new Background();
        background[2].setBackgroundColor(new SolidColor("#DDD"));
        background[2].setBorderWidth(0);
        background[2].setInnerRadius("103%");
        background[2].setOuterRadius("105%");

        configuration.getPane().setStartAngle(-150);
        configuration.getPane().setEndAngle(150);
        configuration.getPane().setBackground(background);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle(new AxisTitle("km/h"));
        yAxis.setMin(0);
        yAxis.setMax(200);
        yAxis.setMinorTickInterval("auto");
        yAxis.setMinorTickWidth(1);
        yAxis.setMinorTickLength(10);
        yAxis.setMinorTickPosition(TickPosition.INSIDE.toString());
        yAxis.setMinorTickColor(new SolidColor("#666"));
        yAxis.setGridLineWidth(0);
        yAxis.setTickPixelInterval(30);
        yAxis.setTickWidth(2);
        yAxis.setTickPosition(TickPosition.INSIDE.toString());
        yAxis.setTickLength(10);
        yAxis.setTickColor(new SolidColor("#666"));

        // FIXME remove initialization after CHARTS-154
        yAxis.setLabels(new Labels());
        yAxis.getLabels().setStep(2);
        // FIXME missing generated API
        // yAxis.getLabels().setRotation("auto");

        PlotBand plotband1 = new PlotBand();
        plotband1.setFrom(0);
        plotband1.setTo(120);
        plotband1.setColor(new SolidColor("#55BF3B"));

        PlotBand plotband2 = new PlotBand();
        plotband2.setFrom(120);
        plotband2.setTo(160);
        plotband2.setColor(new SolidColor("#DDDF0D"));

        PlotBand plotband3 = new PlotBand();
        plotband3.setFrom(160);
        plotband3.setTo(200);
        plotband3.setColor(new SolidColor("#DF5353"));

        yAxis.setPlotBands(plotband1, plotband2, plotband3);

        final ListSeries series = new ListSeries("Speed", 80);
        PlotOptionsGauge plotOptions = new PlotOptionsGauge();
        plotOptions.setTooltip(new SeriesTooltip());
        plotOptions.getTooltip().setValueSuffix(" km/h");
        series.setPlotOptions(plotOptions);
        configuration.setSeries(series);

        runWhileAttached(chart, new Runnable() {
            Random r = new Random(0);

            @Override
            public void run() {
                Integer oldValue = series.getData()[0].intValue();
                Integer newValue = (int) (oldValue + (r.nextDouble() - 0.5) * 20.0);
                series.updatePoint(0, newValue);
            }
        }, 3000, 12000);

        chart.drawChart(configuration);
        return chart;
    }
}
