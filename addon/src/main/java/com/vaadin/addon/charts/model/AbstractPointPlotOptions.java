package com.vaadin.addon.charts.model;

import java.util.Date;

public abstract class AbstractPointPlotOptions extends
        AbstractCommonPlotOptions {

    private Stacking stacking;

    private Number pointStart;
    private Number pointInterval;

    /**
     * Sets the start value on the X-axis.
     * <p>
     * If no X values are given for the points in a series, pointStart defines
     * on which value to start. For example, if a series contains a yearly value
     * starting from 1945, set pointStart to 1945. Defaults to 0.
     * 
     * @param pointStart
     */
    public void setPointStart(Number pointStart) {
        this.pointStart = pointStart;
    }

    /**
     * Sets the start value on the X-axis.
     * <p>
     * If no X values are given for the points in a series, pointStart defines
     * on what value to start. For example, if a series contains a yearly value
     * starting from 1945, set pointStart to 1945. Defaults to 0.
     * 
     * @param pointStart
     */
    public void setPointStart(Date date) {
        pointStart = date.getTime();
    }

    /**
     * @see #setPointStart(Number)
     */
    public Number getPointStart() {
        return pointStart;
    }

    /**
     * Sets the point interval on the X-axis.
     * <p>
     * If no X values are given for the points in a series, pointInterval
     * defines the interval of the values on the X-axis. For example, if a
     * series contains a value for every decade starting from year 0, set
     * pointInterval to 10. Defaults to 1.
     * 
     * @param pointInterval
     */
    public void setPointInterval(Number pointInterval) {
        this.pointInterval = pointInterval;
    }

    /**
     * @see #setPointInterval(Number)
     */
    public Number getPointInterval() {
        return pointInterval;
    }

    /**
     * @see #setStacking(Stacking)
     * @return Stacking or null if not defined
     */
    public Stacking getStacking() {
        return stacking;
    }

    /**
     * Sets whether to stack the values of each series on top of each other.
     * Possible values are null to disable, {@link Stacking#NORMAL} to stack by
     * value or {@link Stacking#PERCENT}. Defaults to null.
     * 
     * @param stacking
     */
    public void setStacking(Stacking stacking) {
        this.stacking = stacking;
    }

}
