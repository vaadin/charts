package com.vaadin.addon.charts.model;

public class PlotOptionsBubble extends AbstractLinePlotOptions {

    private Boolean displayNegative;

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

}
