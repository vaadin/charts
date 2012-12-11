package com.vaadin.addon.charts.model;

/**
 * States for markers (like symbols in plot line)
 */
public class MarkerStates extends AbstractConfigurationObject {
    private State hover;

    public MarkerStates(State hoverState) {
        setHover(hoverState);
    }

    /**
     * @see #setHover(State)
     * @return
     */
    public State getHover() {
        return hover;
    }

    /**
     * Hover state (when mouse is hovered over the marker)
     * 
     * @param hover
     */
    public void setHover(State hover) {
        this.hover = hover;
    }
}
