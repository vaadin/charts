package com.vaadin.addon.charts.model;

/**
 * Applies only to polar charts and angular gauges. This configuration object
 * holds general options for the combined X and Y axes set. Each xAxis or yAxis
 * can reference the pane by index.
 * 
 */
public class Pane extends AbstractConfigurationObject {

    private Number endAngle;
    private Number startAngle;
    private Background[] background;
    private Object[] center;
    private Object size;

    /**
     * Default constructor
     */
    public Pane() {

    }

    /**
     * Construct Pane with given start and end angle
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
     * @return
     */
    public Number getEndAngle() {
        return endAngle;
    }

    /**
     * The end angle of the polar X axis or gauge value axis, given in degrees
     * where 0 is north. Defaults to startAngle + 360.
     * 
     * @param endAngle
     */
    public void setEndAngle(Number endAngle) {
        this.endAngle = endAngle;
    }

    /**
     * @see #setStartAngle(Number)
     * @return
     */
    public Number getStartAngle() {
        return startAngle;
    }

    /**
     * The start angle of the polar X axis or gauge axis, given in degrees where
     * 0 is north. Defaults to 0.
     * 
     * @param startAngle
     */
    public void setStartAngle(Number startAngle) {
        this.startAngle = startAngle;
    }

    /**
     * @see #setBackground(Background[])
     * @return
     */
    public Background[] getBackground() {
        return background;
    }

    /**
     * An object, or array of objects, for backgrounds. Sub options include
     * backgroundColor (which can be solid or gradient), innerWidth, outerWidth,
     * borderWidth, borderColor.
     * 
     * @param background
     */
    public void setBackground(Background[] background) {
        this.background = background;
    }

    /**
     * The center of a polar chart or angular gauge, given as an array of [x, y]
     * positions. Positions can be given as integers that transform to pixels,
     * or as percentages of the plot area size. Defaults to ['50%', '50%'].
     * 
     * @param x
     * @param y
     */
    public void setCenterXY(Object x, Object y) {
        center = new Object[] { x, y };
    }

    /**
     * @see #setCenterXY(Object, Object)
     * @return
     */
    public Object[] getCenter() {
        return center;
    }

    /**
     * Pane's pixel size (Number) or pecentage size (String)
     * 
     * @param size
     */
    public void setSize(Object size) {
        this.size = size;
    }

    /**
     * @see #setSize(Number)
     * @return
     */
    public Object getSize() {
        return size;
    }
}
