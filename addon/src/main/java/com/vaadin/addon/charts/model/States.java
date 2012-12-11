package com.vaadin.addon.charts.model;

/**
 * A wrapper object for all the series options in specific states.
 */
public class States extends AbstractConfigurationObject {

    private State hover;

    public States(State hoverState) {
        hover = hoverState;
    }

    public States() {
    }

    /**
     * @see #setHover(State)
     * @return
     */
    public State getHover() {
        return hover;
    }

    /**
     * Set HoverState for mouse hover events
     * 
     * @param hover
     */
    public void setHover(State hover) {
        this.hover = hover;
    }
}
