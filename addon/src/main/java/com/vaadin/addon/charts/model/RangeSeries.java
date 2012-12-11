package com.vaadin.addon.charts.model;


/**
 * Series for range type data
 */
public class RangeSeries extends AbstractSeries {
    private Number[][] data;

    private Number yAxis;

    /**
     * Default constructor
     */
    public RangeSeries() {
    }

    /**
     * Construct RangeSeries with given name
     * 
     * @param name
     */
    public RangeSeries(String name) {
        super(name);
    }

    /**
     * Construct RangeSeries with given values
     * 
     * @param name
     */
    public RangeSeries(Number[]... numbers) {
        data = numbers;
    }

    /**
     * Construct RangeSeries with given name and values
     * 
     * @param name
     */
    public RangeSeries(String name, Number[]... numbers) {
        this(name);
        data = numbers;
    }

    /**
     * Get numeric data
     * 
     * @return
     */
    public Number[][] getData() {
        return data;
    }

    /**
     * Set numeric data
     * 
     * @param data
     */
    public void setData(Number[]... data) {
        this.data = data;
    }

    /**
     * @see #setyAxis(Number)
     * @return Y Axis number or null if not defined
     */
    public Number getyAxis() {
        return yAxis;
    }

    /**
     * When using dual or multiple y axes, this number defines which yAxis the
     * particular series is connected to. It refers to the index of the axis in
     * the yAxis array, with 0 being the first. Defaults to 0.
     * 
     * @param yAxis
     */
    public void setyAxis(Number yAxis) {
        this.yAxis = yAxis;
    }
}
