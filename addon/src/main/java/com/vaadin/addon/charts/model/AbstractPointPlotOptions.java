package com.vaadin.addon.charts.model;

import java.util.Date;

import com.vaadin.addon.charts.util.Util;

public abstract class AbstractPointPlotOptions extends
        AbstractCommonPlotOptions {

    private Stacking stacking;

    private Integer turboThreshold;
    private Number pointStart;
    private Number pointInterval;
    private Number pointRange;
    private Number pointWidth;

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
        pointStart = Util.toHighchartsTS(date);
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

    /**
     * @return the turbo threshold used for this chart type
     * @see #setTurboThreshold(Integer)
     */
    public Integer getTurboThreshold() {
        return turboThreshold;
    }

    /**
     * Sets the threshold (number of data points) after library will always try
     * to use optimized rendering. For optimized rendering to work, data points
     * can only contain numeric values - no special data item specific settings.
     * <p>
     * The default setting used by library is 1000
     * 
     * @param turboThreshold
     *            the number of data points after the optimized rendering is
     *            forced
     */
    public void setTurboThreshold(Integer turboThreshold) {
        this.turboThreshold = turboThreshold;
    }

    /**
     * @return the point range used for this chart type
     * @see #setPointRange(Number)
     */
    public Number getPointRange() {
        return pointRange;
    }

    /**
     * Sets the X axis range that each point is valid for, determining the width
     * of the column.
     * <p>
     * On a categorized axis, the range will be 1 (one category unit) by
     * default. On linear and datetime eaxes, the range will be computed as the
     * distance between the two closest data points.
     * 
     * @param pointRange
     *            the x axis range each point is valid for
     */
    public void setPointRange(Number pointRange) {
        this.pointRange = pointRange;
    }

    /**
     * @return the point width used for this chart type
     * @see #setPointWidth(Number)
     */
    public Number getPointWidth() {
        return pointWidth;
    }

    /**
     * Sets the pixel value specifying a fixed width for each column or bar.
     * <p>
     * Default null. If not set, the width is calculated from
     * {@link PlotOptionsColumn#setPointPadding(Number) pointPadding} and
     * {@link PlotOptionsColumn#setGroupPadding(Number) groupPadding}.
     * 
     * @param pointWidth
     *            the pixel value specifying the width for each column or bar.
     */
    public void setPointWidth(Number pointWidth) {
        this.pointWidth = pointWidth;
    }

}
