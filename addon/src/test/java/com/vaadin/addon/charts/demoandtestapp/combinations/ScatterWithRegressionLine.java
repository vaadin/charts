package com.vaadin.addon.charts.demoandtestapp.combinations;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.HoverState;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.States;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
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

        XAxis x = new XAxis();
        x.setMin(-0.5);
        x.setMax(5.5);
        conf.addxAxis(x);

        YAxis y = new YAxis();
        y.setMin(0);
        conf.addyAxis(y);

        conf.setTitle("Scatter plot with regression line");

        DataSeries series = new DataSeries(ChartType.LINE);
        series.setName("Regression Line");
        series.setColor(new SolidColor("#AA4643"));

        List<DataSeriesItem> list = new ArrayList<DataSeriesItem>();
        list.add(new DataSeriesItem(0, 1.11));
        list.add(new DataSeriesItem(5, 4.51));
        series.setData(list);

        series.getMarker().setEnabled(false);
        series.setEnableMouseTracking(true);

        States states = new States();
        HoverState hover = new HoverState();
        hover.setLineWidth(0);
        states.setHover(hover);
        series.setStates(states);

        conf.addSeries(series);

        ListSeries listSeries = new ListSeries("Observations", 1, 1.5, 2.8,
                3.5, 3.9, 4.2);
        listSeries.setType(ChartType.SCATTER);

        Marker marker = new Marker(true);
        marker.setRadius(4);
        listSeries.setMarker(marker);

        conf.addSeries(listSeries);

        chart.drawChart(conf);

        return chart;
    }
}
