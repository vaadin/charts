package com.vaadin.addon.charts.examples.other;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.Background;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Pane;
import com.vaadin.addon.charts.model.PlotOptionsSolidGauge;
import com.vaadin.addon.charts.model.SeriesTooltip;
import com.vaadin.addon.charts.model.Stop;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

import java.util.Random;

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
        pane.setBackground(new Background[]{bkg});

        YAxis yaxis = configuration.getyAxis();
        yaxis.setLineWidth(0);
        yaxis.setTickInterval(200);
        yaxis.setTickWidth(0);
        yaxis.setMin(0);
        yaxis.setMax(200);
        yaxis.setTitle(new AxisTitle(""));
        yaxis.getTitle().setY(-70);
        yaxis.setLabels(new Labels());
        yaxis.getLabels().setY(16);
        Stop stop1 = new Stop(0.1f, SolidColor.GREEN);
        Stop stop2 = new Stop(0.5f, SolidColor.YELLOW);
        Stop stop3 = new Stop(0.9f, SolidColor.RED);
        yaxis.setStops(new Stop[] { stop1, stop2, stop3 });

        PlotOptionsSolidGauge plotOptions = new PlotOptionsSolidGauge();
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
