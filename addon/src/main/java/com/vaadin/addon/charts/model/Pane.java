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
 * Applies only to polar charts and angular gauges. This configuration object
 * holds general options for the combined X- and Y- axes set. Each {@link XAxis}
 * or {@link YAxis} can reference the pane by index.
 * 
 */
public class Pane extends AbstractConfigurationObject {

    private Number endAngle;
    private Number startAngle;
    private Background[] background;
    private Object[] center;
    private Object size;

    public Pane() {
    }

    /**
     * Constructs a Pane with the given start and end angle set in degrees.
     * 
     * @param startAngle
     * @param endAngle
     */
    public Pane(Number startAngle, Number endAngle) {
        this.startAngle = startAngle;
        this.endAngle = endAngle;
    }

    /**
     * @see #setEndAngle(Number)
     */
    public Number getEndAngle() {
        return endAngle;
    }

    /**
     * Sets the end angle of the polar X-axis or gauge value axis, given in
     * degrees where 0 is north. Defaults to startAngle + 360.
     * 
     * @param endAngle
     */
    public void setEndAngle(Number endAngle) {
        this.endAngle = endAngle;
    }

    /**
     * @see #setStartAngle(Number)
     */
    public Number getStartAngle() {
        return startAngle;
    }

    /**
     * Sets the start angle of the polar X-axis or gauge axis, given in degrees
     * where 0 is north. Defaults to 0.
     * 
     * @param startAngle
     */
    public void setStartAngle(Number startAngle) {
        this.startAngle = startAngle;
    }

    /**
     * @see #setBackground(Background[])
     */
    public Background[] getBackground() {
        return background;
    }

    /**
     * Sets the backgrounds of the pane. May be one or several.
     * 
     * @param backgrounds
     */
    public void setBackground(Background... backgrounds) {
        this.background = backgrounds;
    }

    /**
     * Sets the center of a polar chart or angular gauge. Positions can be given
     * as integers that transform to pixels, or as percentages of the plot area
     * size. Defaults to ['50%', '50%'].
     * 
     * @param x
     * @param y
     */
    public void setCenterXY(Object x, Object y) {
        center = new Object[] { x, y };
    }

    /**
     * @see #setCenterXY(Object, Object)
     */
    public Object[] getCenter() {
        return center;
    }

    /**
     * Sets the size of the Pane in pixels (Number) or percent (String).
     * 
     * @param size
     */
    public void setSize(Object size) {
        this.size = size;
    }

    /**
     * @see #setSize(Number)
     */
    public Object getSize() {
        return size;
    }
}
