package com.vaadin.addon.charts.examples.other;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.PlotOptionsTreeMap;
import com.vaadin.addon.charts.model.TreeMapLayoutAlgorithm;
import com.vaadin.addon.charts.model.TreeSeries;
import com.vaadin.addon.charts.model.TreeSeriesItem;
import com.vaadin.ui.Component;

public class TreeMapWithColorAxis extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Tree map with color axis";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.TREEMAP);

        // FIXME missing generated API
        // ColorAxis colorAxis = new ColorAxis();
        // colorAxis.setMinColor(new SolidColor("#FFFFFF"));
        // colorAxis.setMaxColor(new SolidColor("#7BB5EF"));
        // chart.getConfiguration().addColorAxis(colorAxis);

        PlotOptionsTreeMap plotOptions = new PlotOptionsTreeMap();
        // FIXME remove toString() once enums are used in model (CHARTS-159)
        plotOptions.setLayoutAlgorithm(TreeMapLayoutAlgorithm.SQUARIFIED
                .toString());

        TreeSeries series = createSeries();

        series.setPlotOptions(plotOptions);

        chart.getConfiguration().addSeries(series);

        chart.getConfiguration().setTitle("Vaadin Charts Treemap");

        return chart;
    }

    private TreeSeries createSeries() {
        List<TreeSeriesItem> items = new ArrayList<TreeSeriesItem>();

        items.add(new TreeSeriesItem("A", 6));
        items.add(new TreeSeriesItem("B", 6));
        items.add(new TreeSeriesItem("C", 4));
        items.add(new TreeSeriesItem("D", 3));
        items.add(new TreeSeriesItem("E", 2));
        items.add(new TreeSeriesItem("F", 2));
        items.add(new TreeSeriesItem("G", 1));

        for (int i = 1; i <= items.size(); i++) {
            items.get(i - 1).setColorValue(i);
        }

        TreeSeries series = new TreeSeries();

        series.setData(items);

        return series;
    }
}
