package com.vaadin.addon.charts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    public Number getMax() {
        return max;
    }

    public void setMax(Number max) {
        this.max = max;
    }

    public Number getMin() {
        return min;
    }

    public void setMin(Number min) {
        this.min = min;
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
     * Sets the extremes at runtime.
     * 
     * @param minimum
     *            Minimum.
     * @param maximum
     *            Maximum.
     * @param redraw
     *            Whether or not to redraw the chart.
     */
    public void setExtremes(Number minimum, Number maximum, boolean redraw) {
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
