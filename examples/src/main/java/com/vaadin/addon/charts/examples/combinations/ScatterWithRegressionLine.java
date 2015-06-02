package com.vaadin.addon.charts.examples.combinations;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PlotOptionsScatter;
import com.vaadin.addon.charts.model.State;
import com.vaadin.addon.charts.model.States;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class ScatterWithRegressionLine extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Scatter with regression line";
    }

    @Override
    protected Component getChart() {

        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();
        Color[] colors = getThemeColors();

        XAxis x = new XAxis();
        x.setMin(-0.5);
        x.setMax(5.5);
        conf.addxAxis(x);

        YAxis y = new YAxis();
        y.setMin(0);
        conf.addyAxis(y);

        conf.setTitle("Scatter plot with regression line");

        DataSeries series = new DataSeries();
        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setColor(colors[1]);
        series.setPlotOptions(plotOptions);
        series.setName("Regression Line");

        List<DataSeriesItem> list = new ArrayList<DataSeriesItem>();
        list.add(new DataSeriesItem(0, 1.11));
        list.add(new DataSeriesItem(5, 4.51));
        series.setData(list);

        plotOptions.setMarker(new Marker(true));
        plotOptions.setEnableMouseTracking(true);

        State hover = new State();
        hover.setLineWidth(0);
        plotOptions.setStates(new States(hover));

        conf.addSeries(series);

        ListSeries listSeries = new ListSeries("Observations", 1, 1.5, 2.8,
                3.5, 3.9, 4.2);
        PlotOptionsScatter plotOptions2 = new PlotOptionsScatter();
        listSeries.setPlotOptions(plotOptions2);

        Marker marker = new Marker(true);
        marker.setRadius(4);
        plotOptions2.setMarker(marker);

        conf.addSeries(listSeries);

        chart.drawChart(conf);

        return chart;
    }
}
