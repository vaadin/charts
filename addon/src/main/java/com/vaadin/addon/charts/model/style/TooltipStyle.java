package com.vaadin.addon.charts.model.style;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;

/**
 * Style options of Tooltip
 */
public class TooltipStyle extends AbstractConfigurationObject {

    private Color backgroundColor;
    private Number borderWidth;
    private Number borderRadius;
    private Style style = new Style();

    /**
     * Get background color of tooltip
     * 
     * @return Background color, null if not defined
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Set background color of tooltip
     * 
     * @param backgroundColor
     *            Background color
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Get border width of tooltip
     * 
     * @return Border width of tooltip, or null if not defined
     */
    public Number getBorderWidth() {
        return borderWidth;
    }

    /**
     * Set border width of tooltip
     * 
     * @param borderWidth
     *            Border width
     */
    public void setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * Get style attributes of tooltip
     * 
     * @return Style attributes
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Set style attributes of tooltip
     * 
     * @param style
     *            Style attributes
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * @return the border radius of the tooltip element
     */
    public Number getBorderRadius() {
        return borderRadius;
    }

    /**
     * Sets the border radius of the tooltip element
     *
     * @param borderRadius the border radius in pixels
     */
    public void setBorderRadius(Number borderRadius) {
        this.borderRadius = borderRadius;
    }

}
