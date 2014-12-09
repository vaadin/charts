package com.vaadin.addon.charts.demoandtestapp.other;

import java.util.Random;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Background;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Pane;
import com.vaadin.addon.charts.model.PlotOptionsSolidGauge;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.YAxis.Stop;
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
        chart.setWidth("500px");

        final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.SOLIDGAUGE);

        Pane pane = new Pane();
        pane.setCenterXY("50%", "85%");
        pane.setSize("140%");
        pane.setStartAngle(-90);
        pane.setEndAngle(90);
        configuration.addPane(pane);

        Background bkg = new Background();
        bkg.setBackgroundColor(new SolidColor("#eeeeee"));
        bkg.setInnerRadius("60%");
        bkg.setOuterRadius("100%");
        bkg.setShape("arc");
        pane.setBackground(bkg);

        YAxis yaxis = configuration.getyAxis();
        yaxis.setLineWidth(0);
        yaxis.setTickPixelInterval(400);
        yaxis.setTickWidth(0);
        yaxis.setMin(0);
        yaxis.setMax(200);
        yaxis.setTitle("Speed");
        yaxis.getTitle().setY(-70);
        yaxis.getLabels().setY(16);
        Stop stop1 = new Stop(0.1f, SolidColor.GREEN);
        Stop stop2 = new Stop(0.5f, SolidColor.YELLOW);
        Stop stop3 = new Stop(0.9f, SolidColor.RED);
        yaxis.setStops(stop1, stop2, stop3);

        PlotOptionsSolidGauge plotOptions = new PlotOptionsSolidGauge();
        // plotOptions.getDataLabels().setY(5);
        // plotOptions.getDataLabels().setBorderWidth(0);
        // plotOptions.getDataLabels().setUseHTML(true);
        // plotOptions.getTooltip().setValueSuffix(" km/h");

        configuration.setPlotOptions(plotOptions);

        final ListSeries series = new ListSeries("Speed", 80);
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
