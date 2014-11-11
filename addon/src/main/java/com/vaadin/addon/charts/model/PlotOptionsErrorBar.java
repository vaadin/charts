package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
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

import java.io.Serializable;

import com.vaadin.addon.charts.model.style.Color;

public class PlotOptionsErrorBar extends AbstractLinePlotOptions {

    private Color stemColor;

    private DashStyle stemDashStyle;

    private Number stemWidth;

    private Color whiskerColor;

    private Serializable whiskerLength;

    private Number whiskerWidth;

    private Number depth;

    private Number groupZPadding;

    @Override
    public ChartType getChartType() {
        return ChartType.ERRORBAR;
    }

    /**
     * @see #setColor(Color)
     */
    public Color getStemColor() {
        return stemColor;
    }

    /**
     * Sets the color of the stem, the vertical line extending from the box to
     * the whiskers. If null, the series color is used. Defaults to null.
     * 
     * @param stemColor
     *            the color of the stem
     */
    public void setStemColor(Color stemColor) {
        this.stemColor = stemColor;
    }

    /**
     * @see #setStemDashStyle(DashStyle)
     */
    public DashStyle getStemDashStyle() {
        return stemDashStyle;
    }

    /**
     * Sets the dash style of the stem, the vertical line extending from the box
     * to the whiskers. Defaults to Solid.
     * 
     * @param stemDashStyle
     *            the dash style for stem
     */
    public void setStemDashStyle(DashStyle stemDashStyle) {
        this.stemDashStyle = stemDashStyle;
    }

    /**
     * @see #setStemWidth(Number)
     * @return the stemWidth
     */
    public Number getStemWidth() {
        return stemWidth;
    }

    /**
     * Sets the width of the stem, the vertical line extending from the box to
     * the whiskers. If null, the width is inherited from the lineWidth option.
     * 
     * @param stemWidth
     *            the stemWidth to set
     */
    public void setStemWidth(Number stemWidth) {
        this.stemWidth = stemWidth;
    }

    /**
     * @see #setWhiskerColor(Color)
     * @return the whisker color
     */
    public Color getWhiskerColor() {
        return whiskerColor;
    }

    /**
     * Sets the color of the whiskers, the horizontal lines marking low and high
     * values. When null, the general series color is used. Defaults to null.
     * 
     * @param whiskerColor
     *            the whisker color to set
     */
    public void setWhiskerColor(Color whiskerColor) {
        this.whiskerColor = whiskerColor;
    }

    /**
     * @see #setWhiskerLength(Number)
     * @return the whiskerLength as percentage string or as a number of pixels
     */
    public Serializable getWhiskerLength() {
        return whiskerLength;
    }

    /**
     * The length of the whiskers, the horizontal lines marking low and high
     * values. This method sets it as a numerical pixel value. Set 0 to disable
     * whiskers. Defaults to 50%.
     * 
     * @see #setWhiskerLengthAsPercentage(Number)
     * 
     * @param whiskerLength
     *            the whisker length to set in pixels
     */
    public void setWhiskerLength(Number whiskerLengthInPixels) {
        whiskerLength = whiskerLengthInPixels;
    }

    /**
     * The length of the whiskers, the horizontal lines marking low and high
     * values. The value is given as a percentage value of the box width. Set 0
     * to disable whiskers. Defaults to 50%.
     * 
     * @see #setWhiskerLength(Number)
     * 
     * @param whiskerLength
     *            the whisker length to set
     */
    public void setWhiskerLengthAsPercentage(Number whiskerLengthAsPercentage) {
        whiskerLength = whiskerLengthAsPercentage + "%";
    }

    /**
     * @see #setWhiskerWidth(Number)
     * 
     * @return the whisker width
     */
    public Number getWhiskerWidth() {
        return whiskerWidth;
    }

    /**
     * Sets the line width of the whiskers, the horizontal lines marking low and
     * high values. When null, the general lineWidth applies.
     * 
     * Defaults to 2.
     * 
     * @param whiskerWidth
     *            the whisker width to set in pixels
     */
    public void setWhiskerWidth(Number whiskerWidth) {
        this.whiskerWidth = whiskerWidth;
    }

    /**
     * @see #setDepth(Number)
     * @return depth
     */
    public Number getDepth() {
        return depth;
    }

    /**
     * Depth of the columns in a 3D column chart. Defaults to 25.
     * 
     * @param depth
     */
    public void setDepth(Number depth) {
        this.depth = depth;
    }

    /**
     * @see #setGroupZPadding(Number)
     * @return groupZPadding
     */
    public Number getGroupZPadding() {
        return groupZPadding;
    }

    /**
     * The spacing between columns on the Z Axis in a 3D chart. Defaults to 1.
     * 
     * @param groupZPadding
     */
    public void setGroupZPadding(Number groupZPadding) {
        this.groupZPadding = groupZPadding;
    }

}