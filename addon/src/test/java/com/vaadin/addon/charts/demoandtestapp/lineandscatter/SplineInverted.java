package com.vaadin.addon.charts.demoandtestapp.lineandscatter;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.ui.Component;

public class SplineInverted extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Spline Inverted";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();
        chart.setWidth("500px");

        Configuration configuration = new Configuration();
        configuration.getChart().setType(ChartType.SPLINE);
        configuration.getChart().setInverted(true);

        configuration.getTitle().setText("Atmosphere Temperature by Altitude");
        configuration.getSubTitle().setText(
                "According to the Standard Atmosphere Model");

        Axis xAxis = configuration.getxAxis();
        xAxis.setReversed(false);
        xAxis.setTitle(new Title("Altitude"));
        Labels labels = new Labels();
        labels.setFormatter("this.value + 'km'");
        labels.setEnabled(true);
        xAxis.setLabels(labels);
        xAxis.setMaxPadding(0.05);
        xAxis.setShowLastLabel(true);

        Axis yAxis = configuration.getyAxis();
        yAxis.setLineWidth(2);
        yAxis.setTitle(new Title("Temperature"));
        yAxis.getTitle().setVerticalAlign(VerticalAlign.HIGH);

        Labels labelsy = new Labels();
        labelsy.setEnabled(true);
        labelsy.setFormatter("this.value + '°'");
        yAxis.setLabels(labelsy);

        configuration.getTooltip().setFormatter("this.x +' km: '+this.y +'°C'");

        PlotOptionsSpline plotOptions = new PlotOptionsSpline();
        plotOptions.setMarker(new Marker(true));
        configuration.setPlotOptions(plotOptions);

        Legend legend = configuration.getLegend();
        legend.setEnabled(false);

        DataSeries series = new DataSeries();
        series.setPlotOptions(new PlotOptionsSpline());
        series.setName("Temperature");
        series.addData(new Number[][] { { 0, 15 }, { 10, -50 }, { 20, -56.5 },
                { 30, -46.5 }, { 40, -22.1 }, { 50, -2.5 }, { 60, -27.7 },
                { 70, -55.7 }, { 80, -76.5 } });
        configuration.setSeries(series);

        chart.drawChart(configuration);
        return chart;
    }
}
