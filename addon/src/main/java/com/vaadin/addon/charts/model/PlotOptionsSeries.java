package com.vaadin.addon.charts.model;

/**
 * Plot options that are specific for ChartType.SERIES charts
 */
public class PlotOptionsSeries extends AbstractPlotOptions {
    private SeriesStates states;
    private Number groupPadding;

    /**
     * Default constructor
     */
    public PlotOptionsSeries() {
    }

    /**
     * Construct PlotOptions with given series states
     * 
     * @param states
     */
    public PlotOptionsSeries(SeriesStates states) {
        this.states = states;
    }

    /**
     * A wrapper object for all the series options in specific states.
     * 
     * @param states
     */
    public void setStates(SeriesStates states) {
        this.states = states;
    }

    /**
     * A wrapper object for all the series options in specific states.<br />
     * Initialize with given HoverState
     * 
     * @param states
     */
    public SeriesStates setStates(HoverState hoverState) {
        states = new SeriesStates(hoverState);
        return states;
    }

    /**
     * @see #setStates(SeriesStates)
     * @return
     */
    public SeriesStates getStates() {
        return states;
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

}