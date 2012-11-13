package com.vaadin.addon.charts.model;

import java.util.Date;

/**
 * Series for range type data
 */
public class RangeSeries extends AbstractSeries {
    private Number[][] data;

    private Number pointStart;
    private Number pointInterval;
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

    @Override
    public ChartType getType() {
        return ChartType.AREARANGE;
    }

    /**
     * If no x values are given for the points in a series, pointStart defines
     * on what value to start. For example, if a series contains one yearly
     * value starting from 1945, set pointStart to 1945. Defaults to 0.
     * 
     * @param pointStart
     */
    public void setPointStart(Number pointStart) {
        this.pointStart = pointStart;
    }

    /**
     * @see #setPointStart(Number)
     * @return
     */
    public Number getPointStart() {
        return pointStart;
    }

    /**
     * If no x values are given for the points in a series, pointInterval
     * defines the interval of the x values. For example, if a series contains
     * one value every decade starting from year 0, set pointInterval to 10.
     * 
     * @param pointInterval
     */
    public void setPointInterval(Number pointInterval) {
        this.pointInterval = pointInterval;
    }

    /**
     * @see #setPointInterval(Number)
     * @return
     */
    public Number getPointInterval() {
        return pointInterval;
    }

    /**
     * If no x values are given for the points in a series, pointStart defines
     * on what value to start. For example, if a series contains one yearly
     * value starting from 1945, set pointStart to 1945. Defaults to 0.
     * 
     * @param pointStart
     */
    public void setPointStart(Date date) {
        pointStart = date.getTime();
    }

    /**
     * When using dual or multiple y axes, this number defines which yAxis the
     * particular series is connected to. It refers to the index of the axis in
     * the yAxis array, with 0 being the first. Defaults to 0.
     * 
     * @return Y Axis number or null if not defined
     */
    public Number getyAxis() {
        return yAxis;
    }

    /**
     * @see #getyAxis()
     * @param yAxis
     */
    public void setyAxis(Number yAxis) {
        this.yAxis = yAxis;
    }
}
