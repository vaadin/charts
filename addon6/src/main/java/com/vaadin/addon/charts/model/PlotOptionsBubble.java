package com.vaadin.addon.charts.model;

import java.io.Serializable;

public class PlotOptionsBubble extends AbstractLinePlotOptions {

    private Boolean displayNegative;
    private Serializable minSize;
    private Serializable maxSize;

    @Override
    public ChartType getChartType() {
        return ChartType.BUBBLE;
    }

    /**
     * @see #setDisplayNegative(Boolean)
     * 
     * @return the false if negative bubbles should be hidden
     */
    public Boolean getDisplayNegative() {
        return displayNegative;
    }

    /**
     * Sets the flag that controls whether to display negative sized bubbles.
     * The threshold is given by the zThreshold option, and negative bubbles can
     * be visualized by setting negativeColor. Defaults to true.
     * 
     * @param displayNegative
     */
    public void setDisplayNegative(Boolean displayNegative) {
        this.displayNegative = displayNegative;
    }

    /**
     * @return the minSize as a number of pixels or percentage string (e.g.
     *         "10%")
     * @see #setMinSize(Number)
     */
    public Serializable getMinSize() {
        return minSize;
    }

    /**
     * Sets the minimum bubble size in pixels. Bubbles will automatically size
     * between the minSize and maxSize to reflect the z value of each bubble.
     * Defaults to 20%.
     * 
     * @param minSize
     *            the minSize to set
     * 
     * @see #setMinSizeAsPercentage(Number)
     */
    public void setMinSize(Number minSize) {
        this.minSize = minSize;
    }

    /**
     * Sets the minimum bubble size in a percentage of the smallest one of the
     * plot width and height. Bubbles will automatically size between the
     * minSize and maxSize to reflect the z value of each bubble. Defaults to 20
     * (%).
     * 
     * @param minSize
     * @see #setMinSize(Number)
     */
    public void setMinSizeAsPercentage(Number minSize) {
        this.minSize = minSize + "%";
    }

    /**
     * @return the maxSize as a number of pixels or percentage string (e.g.
     *         "10%")
     */
    public Serializable getMaxSize() {
        return maxSize;
    }

    /**
     * Sets the maximum bubble size in pixels. Bubbles will automatically size
     * between the minSize and maxSize to reflect the z value of each bubble.
     * Defaults to 20%.
     * 
     * @param maxSize
     *            the maxSize to set
     * @see #setMaxSizeAsPercentage(Number)
     */
    public void setMaxSize(Number maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Sets the maximum bubble size in a percentage of the smallest one of the
     * plot width and height. Bubbles will automatically size between the
     * minSize and maxSize to reflect the z value of each bubble. Defaults to 20
     * (%).
     * 
     * @param maxSize
     * @see #setMaxSize(Number)
     */
    public void setMaxSizeAsPercentage(Number maxSize) {
        this.maxSize = maxSize + "%";
    }

}
