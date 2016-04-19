package com.vaadin.addon.charts.examples.pointclickevent;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;

@SkipFromDemo
public class PointClickCoordinatesBarChart
        extends AbstractPointClickCoordinatesChart {

    public PointClickCoordinatesBarChart() {
        super(ChartType.BAR);
    }

    @Override
    protected Chart createChart() {
        Chart chart = super.createChart();

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Historic World Population by Region");
        conf.setSubTitle("Source: Wikipedia.org");

        Legend legend = new Legend();
        legend.setLabelFormatter(
                "function() { return this.name + ' (click to hide)'; }");
        conf.setLegend(legend);

        return chart;
    }

    @Override
    public void addSeries(Configuration conf) {
        conf.addSeries(new ListSeries("Year 1800", 107, 31, 635, 203, 2));
        conf.addSeries(new ListSeries("Year 1900", 133, 156, 947, 408, 6));
        conf.addSeries(new ListSeries("Year 2008", 973, 914, 4054, 732, 34));
    }

}
