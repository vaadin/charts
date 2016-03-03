package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
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

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vaadin.addon.charts.util.Util;

public abstract class Axis extends AbstractConfigurationObject {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected Number min;
    protected Number max;

    private Integer axisIndex;

    @JsonIgnore
    private ChartConfiguration configuration;

    void setAxisIndex(int i) {
        axisIndex = i;
    }

    Integer getAxisIndex() {
        return axisIndex;
    }

    /**
     * @see #setMin(Number)
     * @return the minimum value of the axis or null
     */
    public Number getMin() {
        return min;
    }

    /**
     * The minimum value of the axis. If null the min value is automatically
     * calculated. If the startOnTick option is true, the min value might be
     * rounded down. Defaults to null.
     *
     * @param min
     */
    public void setMin(Number min) {
        this.min = min;
    }

    /**
     * The minimum value of the axis as Date.
     *
     * @param min
     * @see #setMin(Number)
     */
    public void setMin(Date min) {
        this.min = Util.toHighchartsTS(min);
    }

    /**
     * @see #setMax(Number)
     * @return Maximum value of axis or null
     */
    public Number getMax() {
        return max;
    }

    /**
     * The maximum value of the axis. If null, the max value is automatically
     * calculated. If the endOnTick option is true, the max value might be
     * rounded up. The actual maximum value is also influenced by
     * chart.alignTicks. Defaults to null.
     *
     * @param max
     */
    public void setMax(Number max) {
        this.max = max;
    }

    /**
     * The maximum value of the axis as Date.
     *
     * @param max
     * @see #setMax(Number)
     */
    public void setMax(Date max) {
        this.max = Util.toHighchartsTS(max);
    }


    /**
     * Sets the minimum and maximum of the axes after rendering has finished. If
     * the startOnTick and endOnTick options are true, the minimum and maximum
     * values are rounded off to the nearest tick. To prevent this, these
     * options can be set to false before calling setExtremes.
     * 
     * @param min
     *            The new minimum value
     * @param max
     *            The new maximum value
     */
    public void setExtremes(Number min, Number max) {
        this.setExtremes(min, max, true, true);
    }

    /**
     * The minimun and maximum value of the axis as Date.
     *
     * @see #setExtremes(Number, Number)
     */
    public void setExtremes(Date min, Date max) {
        this.setExtremes(min, max, true, true);
    }

    /**
     * Sets the extremes at runtime.
     * 
     * @param min
     *            Minimum.
     * @param max
     *            Maximum.
     * @param redraw
     *            Whether or not to redraw the chart.
     */
    public void setExtremes(Number min, Number max, boolean redraw) {
        this.setExtremes(min, max, redraw, true);
    }

    /**
     * The minimun and maximum value of the axis as Date.
     *
     * @see #setExtremes(Number, Number, boolean)
     */
    public void setExtremes(Date min, Date max, boolean redraw) {
        this.setExtremes(min, max, redraw, true);
    }

    /**
     * Run-time modification of the axis extremes.
     * 
     * @param minimum
     *            New minimum value.
     * @param maximum
     *            New maximum value.
     * @param redraw
     *            Whether or not to redraw the chart.
     * @param animate
     *            Whether or not to animate the rescaling.
     */
    public void setExtremes(Number minimum, Number maximum, boolean redraw,
            boolean animate) {
        min = minimum;
        max = maximum;
        if (configuration != null) {
            configuration.fireAxesRescaled(this, minimum, maximum, redraw,
                    animate);
        }
    }

    /**
     * The minimun and maximum value of the axis as Date.
     *
     * @see #setExtremes(Number, Number, boolean, boolean)
     */
    public void setExtremes(Date minimum, Date maximum, boolean redraw,
        boolean animate) {
        setMin(minimum);
        setMax(maximum);
        if (configuration != null) {
            configuration.fireAxesRescaled(this, min, max, redraw,
                animate);
        }
    }

    /**
     * Returns the configuration this axis is bound to.
     * 
     * @return The configuration.
     */
    ChartConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * Sets the configuration this axis is bound to. This method is
     * automatically called by configuration, when the axis is added to it.
     * 
     * @param configuration
     *            Configuration this object is linked to.
     */
    public void setConfiguration(ChartConfiguration configuration) {
        this.configuration = configuration;
    }
}
