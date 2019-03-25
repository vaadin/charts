package com.vaadin.addon.charts.examples.lineandscatter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.ui.Component;

@SkipFromDemo
public class BasicLineZIndex extends BasicLine {

    @Override
    protected Component getChart() {
        Chart chart = (Chart) super.getChart();
        Configuration configuration = chart.getConfiguration();
        PlotOptionsLine options = (PlotOptionsLine) configuration
                .getPlotOptions(ChartType.LINE);
        options.setLineWidth(5);
        List<Series> seriesList = configuration.getSeries();
        AtomicInteger seriesCount = new AtomicInteger(seriesList.size());
        seriesList.forEach(
                series -> series.setZIndex(seriesCount.decrementAndGet()));
        return chart;
    }

}
