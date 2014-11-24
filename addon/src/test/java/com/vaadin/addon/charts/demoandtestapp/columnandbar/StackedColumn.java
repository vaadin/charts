package com.vaadin.addon.charts.demoandtestapp.columnandbar;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.StackLabels;
import com.vaadin.addon.charts.model.Stacking;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class StackedColumn extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Stacked Column";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.COLUMN);

        Configuration conf = chart.getConfiguration();

        conf.setTitle(new Title("Stacked column chart"));

        XAxis xAxis = new XAxis();
        xAxis.setCategories(new String[] { "Apples", "Oranges", "Pears",
                "Grapes", "Bananas" });
        conf.addxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setMin(0);
        yAxis.setTitle(new Title("Total fruit consumption"));
        StackLabels sLabels = new StackLabels(true);
        yAxis.setStackLabels(sLabels);
        conf.addyAxis(yAxis);

        Legend legend = new Legend();
        legend.setHorizontalAlign(HorizontalAlign.RIGHT);
        legend.setFloating(true);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setX(-100);
        legend.setY(20);
        conf.setLegend(legend);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("function() { return '<b>'+ this.x +'</b><br/>"
                + "'+this.series.name +': '+ this.y +'<br/>'+'Total: '+ this.point.stackTotal;}");
        conf.setTooltip(tooltip);

        PlotOptionsColumn plotOptions = new PlotOptionsColumn();
        plotOptions.setStacking(Stacking.NORMAL);
        Labels labels = new Labels(true);
        labels.setColor(new SolidColor("white"));
        plotOptions.setDataLabels(labels);
        conf.setPlotOptions(plotOptions);

        conf.addSeries(new ListSeries("John", new Number[] { 5, 3, 4, 7, 2 }));
        conf.addSeries(new ListSeries("Jane", new Number[] { 2, 2, 3, 2, 1 }));
        conf.addSeries(new ListSeries("Joe", new Number[] { 3, 4, 4, 2, 5 }));

        chart.drawChart(conf);
        return chart;
    }
}
