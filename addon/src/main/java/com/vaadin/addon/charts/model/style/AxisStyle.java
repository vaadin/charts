package com.vaadin.addon.charts.model.style;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 2.0
 * (CVALv2).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv2 along with this program.
 * If not, see <http://vaadin.com/license/cval-2.0>.
 * #L%
 */

import com.vaadin.addon.charts.model.AbstractConfigurationObject;

/**
 * Styles of Axis
 */
public class AxisStyle extends AbstractConfigurationObject {

    private TickIntervalStyle minorTickInterval;
    private Color lineColor;
    private Number lineWidth;
    private Number tickWidth;
    private Color tickColor;
    private Color gridLineColor;
    private Number gridLineWidth;
    private Color alternateGridColor;

    private final StyleWrapper title = new StyleWrapper();
    private final StyleWrapper subtitle = new StyleWrapper();
    private final StyleWrapper labels = new StyleWrapper();

    /**
     * @see #setMinorTickInterval(TickIntervalStyle)
     * @return
     */
    public TickIntervalStyle getMinorTickInterval() {
        return minorTickInterval;
    }

    /**
     * Tick interval in scale units for the minor ticks. On a linear axis, if
     * AUTO, the minor tick interval is calculated as a fifth of the
     * tickInterval. If null, minor ticks are not shown.
     * 
     * On logarithmic axes, the unit is the power of the value. For example,
     * setting the minorTickInterval to 1 puts one tick on each of 0.1, 1, 10,
     * 100 etc. Setting the minorTickInterval to 0.1 produces 9 ticks between 1
     * and 10, 10 and 100 etc. A minorTickInterval of AUTO on a log axis results
     * in a best guess, attempting to enter approximately 5 minor ticks between
     * each major tick. Defaults to null.
     * 
     * @param minorTickInterval
     */
    public void setMinorTickInterval(TickIntervalStyle minorTickInterval) {
        this.minorTickInterval = minorTickInterval;
    }

    /**
     * @see #setLineColor(Color)
     * @return
     */
    public Color getLineColor() {
        return lineColor;
    }

    /**
     * The color of the line marking the axis itself. Defaults to "#C0D0E0".
     * 
     * @param lineColor
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * @see #setLineWidth(Number)
     * @return
     */
    public Number getLineWidth() {
        return lineWidth;
    }

    /**
     * The width of the line marking the axis itself. Defaults to 1.
     * 
     * @param lineWidth
     */
    public void setLineWidth(Number lineWidth) {
        this.lineWidth = lineWidth;
    }

    /**
     * @see #setTickWidth(Number)
     * @return
     */
    public Number getTickWidth() {
        return tickWidth;
    }

    /**
     * The pixel width of the major tick marks. Defaults to 1.
     * 
     * @param tickWidth
     */
    public void setTickWidth(Number tickWidth) {
        this.tickWidth = tickWidth;
    }

    /**
     * @see #setTickColor(Color)
     * @return
     */
    public Color getTickColor() {
        return tickColor;
    }

    /**
     * Color for the main tick marks. Defaults to #C0D0E0.
     * 
     * @param tickColor
     */
    public void setTickColor(Color tickColor) {
        this.tickColor = tickColor;
    }

    /**
     * Get title style
     * 
     * @return Title style
     */
    public Style getTitle() {
        return title.getStyle();
    }

    /**
     * Set title style
     * 
     * @param style
     *            Title style
     */
    public void setTitle(Style style) {
        title.setStyle(style);
    }

    /**
     * Get subtitle style
     * 
     * @return Subtitle style
     */
    public Style getSubtitle() {
        return subtitle.getStyle();
    }

    /**
     * Set subtitle style
     * 
     * @param style
     *            Subtitle style
     */
    public void setSubtitle(Style style) {
        subtitle.setStyle(style);
    }

    /**
     * Get labels style
     * 
     * @return Labels style
     */
    public Style getLabels() {
        return labels.getStyle();
    }

    /**
     * Set labels style
     * 
     * @param style
     *            Labels style
     */
    public void setLabels(Style style) {
        labels.setStyle(style);
    }

    /**
     * @see #setGridLineWidth(Number)
     * 
     * @return Grid line width or null if not defined
     */
    public Number getGridLineWidth() {
        return gridLineWidth;
    }

    /**
     * The width of the grid lines extending the ticks across the plot area.
     * Defaults to 0.
     * 
     * @param gridLineWidth
     *            Grid line width
     */
    public void setGridLineWidth(Number gridLineWidth) {
        this.gridLineWidth = gridLineWidth;
    }

    /**
     * @see #setAlternateGridColor(Color)
     * 
     * @return Alternate grid color, null if not defined
     */
    public Color getAlternateGridColor() {
        return alternateGridColor;
    }

    /**
     * When using an alternate grid color, a band is painted across the plot
     * area between every other grid line. Defaults to null.
     * 
     * @param alternateGridColor
     *            Alternate grid color
     */
    public void setAlternateGridColor(Color alternateGridColor) {
        this.alternateGridColor = alternateGridColor;
    }

    /**
     * @see #setGridLineColor(Color)
     * 
     * @return Color of grid lines, null if not defined
     */
    public Color getGridLineColor() {
        return gridLineColor;
    }

    /**
     * Color of the grid lines extending the ticks across the plot area.
     * Defaults to "#C0C0C0".
     * 
     * @param gridLineColor
     *            Color of grid lines
     */
    public void setGridLineColor(Color gridLineColor) {
        this.gridLineColor = gridLineColor;
    }

}
