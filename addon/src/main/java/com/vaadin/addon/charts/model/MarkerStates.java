package com.vaadin.addon.charts.model;

/**
 * States for markers (like symbols in plot line)
 */
public class MarkerStates extends AbstractConfigurationObject {
    private HoverState hover;

    public MarkerStates(HoverState hoverState) {
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
     * Hover state (when mouse is hovered over the marker)
     * 
     * @param hover
     */
    public void setHover(HoverState hover) {
        this.hover = hover;
    }
}
