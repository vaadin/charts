package com.vaadin.addon.charts.model;

/**
 * A wrapper object for all the series options in specific states.
 */
public class States extends AbstractConfigurationObject {

    private HoverState hover;

    /**
     * @see #setHover(HoverState)
     * @return
     */
    public HoverState getHover() {
        return hover;
    }

    /**
     * Set HoverState for mouse hover events
     * 
     * @param hover
     */
    public void setHover(HoverState hover) {
        this.hover = hover;
    }
}
