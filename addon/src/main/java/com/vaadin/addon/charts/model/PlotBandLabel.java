package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Style;

/**
 * Text labels for the plot bands
 */
public class PlotBandLabel extends AbstractConfigurationObject {
    private String text;
    private Style style;

    /**
     * Construct a PlotBandLabel from given text
     * 
     * @param text
     */
    public PlotBandLabel(String text) {
        this.text = text;
    }

    /**
     * The string text itself. A subset of HTML is supported.
     * 
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @see #setText(String)
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * CSS styles for the text label.
     * 
     * @param style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * @see #setStyle(LabelStyle)
     * @return
     */
    public Style getStyle() {
        return style;
    }
}
