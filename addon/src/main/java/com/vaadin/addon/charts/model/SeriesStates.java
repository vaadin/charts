package com.vaadin.addon.charts.model;

/**
 * A wrapper object for all the series options in specific states.
 * 
 */
public class SeriesStates extends AbstractConfigurationObject {
    private HoverState hover;

    /**
     * Construct SeriesStates with given hoverstate
     * 
     * @param hoverState
     */
    public SeriesStates(HoverState hoverState) {
        setHover(hoverState);
    }

    /**
     * @see #setHover(HoverState)
     * @return
     */
    public HoverState getHover() {
        return hover;
    }

    /**
     * Set state for hover event
     * 
     * @param hover
     */
    public void setHover(HoverState hover) {
        this.hover = hover;
    }
}
