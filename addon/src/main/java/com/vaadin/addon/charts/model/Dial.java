package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.SolidColor;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */

/**
 * The Dial class contains options for the dial or arrow pointer of a gauge
 * chart.
 */
public class Dial extends AbstractConfigurationObject {

    private String backgroundColor;
    private String borderColor;
    private Number borderWidth;
    private Number topWidth;
    private String radius;
    private Number baseWidth;
    private String baseLength;
    private String rearLength;

    public Dial() {
    }

    /**
     * @see #setRadius(String)
     * @return The radius or length of the dial in percent.
     */
    public String getRadius() {
        return radius;
    }

    /**
     * Sets the radius or length of the dial. The value is specified in
     * percentages relative to the radius of the gauge itself. Defaults to 80%.
     * 
     * @param radius
     */
    public void setRadius(String radius) {
        this.radius = radius;
    }

    /**
     * @see #setBaseWidth(Number)
     * @return The pixel width of the base of the dial.
     */
    public Number getBaseWidth() {
        return baseWidth;
    }

    /**
     * Sets the pixel width of the base of the gauge dial. The base is the part
     * closest to the pivot, defined by baseLength. Defaults to 3.
     * 
     * @param baseWidth
     */
    public void setBaseWidth(Number baseWidth) {
        this.baseWidth = baseWidth;
    }

    /**
     * @see #setBaseLength(String)
     * @return The base length of the dial.
     */
    public String getBaseLength() {
        return baseLength;
    }

    /**
     * The length of the dial's base part, relative to the total radius or
     * length of the dial. Defaults to 70%.
     * 
     * @param baseLength
     */
    public void setBaseLength(String baseLength) {
        this.baseLength = baseLength;
    }

    /**
     * @see #setRearLength(Object)
     * @return The rear length of the dial.
     */
    public Object getRearLength() {
        return rearLength;
    }

    /**
     * The length of the dial's rear end, the part that extends out on the other
     * side of the pivot. Relative to the dial's length. Defaults to 10%.
     * 
     * @param rearLength
     */
    public void setRearLength(String rearLength) {
        this.rearLength = rearLength;
    }

    /**
     * Returns current background color as an HTML color string.
     * 
     * @return Current background color.
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets the background color.
     * 
     * @param backgroundColor
     *            Color to set.
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Sets the background color.
     * 
     * @param color
     *            Color to set.
     */
    public void setBackgroundColor(SolidColor color) {
        this.backgroundColor = color.toString();
    }

    /**
     * Returns current border color as an HTML color string.
     * 
     * @return Current border color.
     */
    public String getBorderColor() {
        return borderColor;
    }

    /**
     * Sets the color of the border.
     * 
     * @param borderColor
     *            Color of the border.
     */
    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * Sets the color of the border.
     * 
     * @param borderColor
     *            Color of the border.
     */
    public void setBorderColor(SolidColor borderColor) {
        this.borderColor = borderColor.toString();
    }

    /**
     * Returns current border width.
     * 
     * @return Current border width.
     */
    public Number getBorderWidth() {
        return borderWidth;
    }

    /**
     * Sets the new border width.
     * 
     * @param borderWidth
     *            Border width.
     */
    public void setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * Returns the width of the top of the dial.
     * 
     * @return Width of the top of the dial.
     */
    public Number getTopWidth() {
        return topWidth;
    }

    /**
     * Sets the width of the top of the dial, closest to the perimeter. The
     * pivot narrows in form from the base to the top.
     * 
     * @param topWidth
     *            Width of the top dial.
     */
    public void setTopWidth(Number topWidth) {
        this.topWidth = topWidth;
    }

}
