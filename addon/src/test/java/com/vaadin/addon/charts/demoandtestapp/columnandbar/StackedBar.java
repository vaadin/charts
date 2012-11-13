package com.vaadin.addon.charts.demoandtestapp.columnandbar;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsSeries;
import com.vaadin.addon.charts.model.Stacking;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class StackedBar extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Stacked bar";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.BAR);

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Stacked bar chart");

        XAxis x = new XAxis();
        x.setCategories("Apples", "Oranges", "Pears", "Grapes", "Bananas");
        conf.addxAxis(x);

        YAxis y = new YAxis();
        y.setMin(0);
        y.setTitle("Total fruit consumption");
        conf.addyAxis(y);

        Legend legend = new Legend();
        legend.setBackgroundColor("#FFFFFF");
        legend.setReversed(true);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("function() { return ''+  this.series.name +': '+ this.y +''; }");
        conf.setTooltip(tooltip);

        PlotOptionsSeries plot = new PlotOptionsSeries();
        plot.setStacking(Stacking.NORMAL);
        conf.getPlotOptions().setSeries(plot);

        conf.addSeries(new ListSeries("John", 5, 3, 4, 7, 2));
        conf.addSeries(new ListSeries("Jane", 2, 2, 3, 2, 1));
        conf.addSeries(new ListSeries("Joe", 3, 4, 4, 2, 5));

        chart.drawChart(conf);

        return chart;
    }
}
