package com.vaadin.addon.charts.model;

/**
 * Plot options that are specific for ChartType.COLUMNRANGE charts
 */
public class PlotOptionsColumnRange extends AbstractPlotOptions {

    @Override
    public ChartType getChartType() {
        return ChartType.COLUMNRANGE;
    }

}
