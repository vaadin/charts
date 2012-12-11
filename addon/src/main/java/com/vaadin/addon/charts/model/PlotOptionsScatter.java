package com.vaadin.addon.charts.model;

/**
 * Plot options that are specific for ChartType.SCATTER charts
 */
public class PlotOptionsScatter extends AbstractPlotOptions {

    @Override
    public ChartType getChartType() {
        return ChartType.SCATTER;
    }

}
