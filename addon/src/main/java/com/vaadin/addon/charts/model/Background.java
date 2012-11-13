package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;

/**
 * An object, or array of objects, for backgrounds. Sub options include
 * backgroundColor (which can be solid or gradient), innerWidth, outerWidth,
 * borderWidth, borderColor.
 */
public class Background extends AbstractConfigurationObject {
    private Color backgroundColor;
    private Color borderColor;
    private Number innerWidth;
    private Number outerWidth;
    private Number borderWidth;
    private String outerRadius;
    private String innerRadius;

    /**
     * @see #setBackgroundColor(Color)
     * @return
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Pane's background color
     * 
     * @param backgroundColor
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * @see #setBorderColor(Color)
     * @return
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * Pane's border color
     * 
     * @param borderColor
     */
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * @see #setInnerWidth(Number)
     * @return
     */
    public Number getInnerWidth() {
        return innerWidth;
    }

    /**
     * Pane's inner width
     * 
     * @param innerWidth
     */
    public void setInnerWidth(Number innerWidth) {
        this.innerWidth = innerWidth;
    }

    /**
     * @see #setOuterWidth(Number)
     * @return
     */
    public Number getOuterWidth() {
        return outerWidth;
    }

    /**
     * Pane's outer width
     * 
     * @param outerWidth
     */
    public void setOuterWidth(Number outerWidth) {
        this.outerWidth = outerWidth;
    }

    /**
     * @see #setOuterWidth(Number)
     * @return
     */
    public Number setBorderWidth() {
        return borderWidth;
    }

    /**
     * Pane's border width
     * 
     * @param borderWidth
     */
    public void setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * Pane's outer radius in percentage string like "110%"
     * 
     * @param outerRadius
     */
    public void setOuterRadius(String outerRadius) {
        this.outerRadius = outerRadius;
    }

    /**
     * @see #setOuterRadius(String)
     * @return
     */
    public String getOuterRadius() {
        return outerRadius;
    }

    /**
     * Pane's innner radius in percentage string like "110%"
     * 
     * @param outerRadius
     */
    public void setInnerRadius(String innerRadius) {
        this.innerRadius = innerRadius;
    }

    /**
     * @see #setInnerRadius(String)
     * @return
     */
    public String getInnerRadius() {
        return innerRadius;
    }

}
