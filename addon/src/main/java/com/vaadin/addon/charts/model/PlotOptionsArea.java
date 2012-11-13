package com.vaadin.addon.charts.model;

/**
 * Plot options for ChartType.AREA
 */
public class PlotOptionsArea extends AbstractPlotOptions {
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
