package com.vaadin.addon.charts.model;

/**
 * Plot options that are specific for ChartType.SPLINE charts
 */
public class PlotOptionsSpline extends AbstractPlotOptions {

    @Override
    public ChartType getChartType() {
        return ChartType.SPLINE;
    }
}
