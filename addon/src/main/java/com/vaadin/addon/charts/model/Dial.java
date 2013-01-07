package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 2.0
 * (CVALv2).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv2 along with this program.
 * If not, see <http://vaadin.com/license/cval-2.0>.
 * #L%
 */

/**
 * The Dial class contains options for the dial or arrow pointer of a gauge
 * chart.
 */
public class Dial {

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
     * @see #setRearLength(String)
     * @return The rear length of the dial.
     */
    public String getRearLength() {
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

}
