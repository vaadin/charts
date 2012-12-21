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
 * Options for the dial or arrow pointer of the gauge.
 */
public class Dial {

    private Object radius;
    private Number baseWidth;
    private Object baseLength;
    private Object rearLength;

    public Dial() {

    }

    /**
     * @see #setRadius(Object)
     * @return
     */
    public Object getRadius() {
        return radius;
    }

    /**
     * The radius or length of the dial, in percentages relative to the radius
     * of the gauge itself. Defaults to 80%.
     * 
     * @param radius
     */
    public void setRadius(Object radius) {
        this.radius = radius;
    }

    /**
     * @see #setBaseWidth(Number)
     * @return
     */
    public Number getBaseWidth() {
        return baseWidth;
    }

    /**
     * The pixel width of the base of the gauge dial. The base is the part
     * closest to the pivot, defined by baseLength. Defaults to 3.
     * 
     * @param baseWidth
     */
    public void setBaseWidth(Number baseWidth) {
        this.baseWidth = baseWidth;
    }

    /**
     * @see #setBaseWidth(Object)
     * @return
     */
    public Object getBaseLength() {
        return baseLength;
    }

    /**
     * The length of the dial's base part, relative to the total radius or
     * length of the dial. Defaults to 70%.
     * 
     * @param baseLength
     */
    public void setBaseLength(Object baseLength) {
        this.baseLength = baseLength;
    }

    /**
     * @see #setRearLength(Object)
     * @return
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
    public void setRearLength(Object rearLength) {
        this.rearLength = rearLength;
    }

}
