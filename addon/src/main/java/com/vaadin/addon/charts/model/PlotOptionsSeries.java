package com.vaadin.addon.charts.model;

/**
 * Generic plot options that are are used for all chart types.
 */
public class PlotOptionsSeries extends AbstractPlotOptions {
    private Number groupPadding;

    /**
     * Default constructor
     */
    public PlotOptionsSeries() {
    }

    /**
     * Padding between each value groups, in x axis units. Defaults to 0.2.
     * 
     * @param groupPadding
     */
    public void setGroupPadding(Number groupPadding) {
        this.groupPadding = groupPadding;
    }

    /**
     * @see #setGroupPadding(Number)
     * @return
     */
    public Number getGroupPadding() {
        return groupPadding;
    }

    @Override
    public ChartType getChartType() {
        return ChartType.LINE;
    }

}