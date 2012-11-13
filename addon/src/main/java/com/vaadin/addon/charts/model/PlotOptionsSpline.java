package com.vaadin.addon.charts.model;

/**
 * Plot options that are specific for ChartType.SPLINE charts
 */
public class PlotOptionsSpline extends AbstractPlotOptions {
    private AreaStates states;

    /**
     * Set wrapper object for all the series options in specific states.
     * 
     * @param areaStates
     */
    public void setStates(AreaStates states) {
        this.states = states;
    }

    /**
     * @see #setStates(AreaStates)
     * @return
     */
    public AreaStates getStates() {
        return states;
    }
}
