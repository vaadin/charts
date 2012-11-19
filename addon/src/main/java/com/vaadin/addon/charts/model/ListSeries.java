package com.vaadin.addon.charts.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * A list of numerical values. In this case, the numerical values will be
 * interpreted and y values, and x values will be automatically calculated,
 * either starting at 0 and incrementing by 1, or from pointStart and
 * pointInterval given in the plotOptions. If the axis is has categories, these
 * will be used. This option is not available for range series.
 * 
 */
public class ListSeries extends AbstractSeries {

    private List<Number> data = new ArrayList<Number>();
    private Number pointStart;
    private Number pointInterval;
    private Number yAxis;

    /**
     * Default constructor
     */
    public ListSeries() {
    }

    /**
     * Constructor with given series name
     * 
     * @param name
     */
    public ListSeries(String name) {
        super(name);
    }

    /**
     * Constructor with given array of values
     * 
     * @param name
     */
    public ListSeries(Number... numbers) {
        Collections.addAll(data, numbers);
    }

    /**
     * Constructor with given series name and array of values
     * 
     * @param name
     */
    public ListSeries(String name, Number... numbers) {
        this(name);
        setData(numbers);
    }

    /**
     * Return array of numeric values
     * 
     * @return
     */
    public Number[] getData() {
        return data.toArray(new Number[data.size()]);
    }

    /**
     * Set array of numeric values as values.
     * 
     * @return
     */
    public void setData(Number... data) {
        this.data.clear();
        Collections.addAll(this.data, data);
    }

    /**
     * Set the given list of numeric values as values.
     * 
     * @return
     */
    public void setData(List<Number> data) {
        this.data = data;
    }

    /**
     * Adds a given number to the series and immediately updates the chart if it
     * already has been drawn. If the chart has not yet been drawn all items are
     * added to the chart when it is drawn the first time.
     * 
     * @param number
     *            the number to be added to series
     */
    public void addData(Number number) {
        addData(number, true);
    }

    /**
     * Adds a given number to the series and optionally immediately updates the
     * chart if it has been drawn.
     * 
     * Calling this method with false for the second parameter is useful if you
     * want to add many items without a client/server round-trip for each item
     * added.
     * 
     * @param number
     * @param updateChartImmediately
     */
    public void addData(Number number, boolean updateChartImmediately) {
        data.add(number);
        if (updateChartImmediately && getConfiguration() != null) {
            getConfiguration().fireDataAdded(this,
                    new DataSeriesItem(data.size() - 1, number));
        }
    }

    @Override
    public ChartType getType() {
        return ChartType.LINE;
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

    /**
     * Updates the value of the data point at pointIndex to newValue and
     * immediately update it on the chart.
     * 
     * @param pointIndex
     * @param newValue
     */
    public void updatePoint(int pointIndex, Number newValue) {
        data.remove(pointIndex);
        data.add(pointIndex, newValue);
        if (getConfiguration() != null) {
            getConfiguration().fireDataUpdated(this, newValue, pointIndex);
        }
    }
}
