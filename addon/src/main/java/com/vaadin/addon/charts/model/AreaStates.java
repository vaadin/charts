package com.vaadin.addon.charts.model;

/**
 * A wrapper object for all the series options in specific states.
 */
public class AreaStates extends AbstractConfigurationObject {
    private HoverState hover;

    /**
     * Constructs AreaStates with HoverState
     * 
     * @param hoverState
     */
    public AreaStates(HoverState hoverState) {
        setHover(hoverState);
    }

    /**
     * Returns the Hover object of the AreaStates
     * 
     * @return
     */
    public HoverState getHover() {
        return hover;
    }

    /**
     * Sets the Hover object of the AreaStates
     * 
     * @param hover
     */
    public void setHover(HoverState hover) {
        this.hover = hover;
    }
}
