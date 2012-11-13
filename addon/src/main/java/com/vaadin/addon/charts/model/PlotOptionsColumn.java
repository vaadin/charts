package com.vaadin.addon.charts.model;

/**
 * Plot options that are specific for ChartType.COLUMN charts
 */
public class PlotOptionsColumn extends AbstractPlotOptions {
    private Number pointPadding;
    private Number borderWidth;
    private Number groupPadding;

    /**
     * Padding between each column or bar, in x axis units. Defaults to 0.1.
     * 
     * @return Padding or null if not set
     */
    public Number getPointPadding() {
        return pointPadding;
    }

    /**
     * @see #getPointPadding()
     * @param pointPadding
     */
    public void setPointPadding(Number pointPadding) {
        this.pointPadding = pointPadding;
    }

    /**
     * The width of the border surronding each column or bar. Defaults to 1.
     * 
     * @return Border with or null if not set
     */
    public Number getBorderWidth() {
        return borderWidth;
    }

    /**
     * @see #getBorderWidth()
     * @param borderWidth
     */
    public void setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * Padding between each value groups, in x axis units. Defaults to 0.2.
     * 
     * @param groupPadding
     */
    public void setGroupPadding(Number groupPadding) {
        this.groupPadding = groupPadding;
    }

    /**
     * @see #setGroupPadding(Number)
     * @return
     */
    public Number getGroupPadding() {
        return groupPadding;
    }
}
