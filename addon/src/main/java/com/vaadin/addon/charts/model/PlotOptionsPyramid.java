package com.vaadin.addon.charts.model;

import java.io.Serializable;

import com.vaadin.addon.charts.model.style.SolidColor;

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

/**
 * Options for Pyramid chart. These same options are used as a base class for
 * Funnel chart options.
 * 
 * @author miki
 *
 */
public class PlotOptionsPyramid extends AbstractPlotOptions {

    private Boolean allowPointSelect;
    private SolidColor borderColor;
    private Number borderWidth;
    private Object[] center;
    private Number depth;
    private Serializable height;
    private Number minSize;
    private Boolean reversed;
    private Boolean shadow;
    private Number slicedOffset;
    private Boolean visible;
    private Serializable width;

    public PlotOptionsPyramid() {
        super();
    }

    @Override
    public ChartType getChartType() {
        return ChartType.PYRAMID;
    }

    /**
     * @see #setWidth(Number)
     * @see #setWidthAsPercentage(Number)
     * @return the width of the funnel or pyramid, value can be String
     *         (percentage of the plot value) or Number (pixel value)
     */
    public Serializable getWidth() {
        return width;
    }

    /**
     * Sets the width of the funnel or pyramid in pixels
     * 
     * @param width
     *            the pixel width of the funnel chart
     * @see #setWidthAsPercentage(Number)
     */
    public void setWidth(Number width) {
        this.width = width;
    }

    /**
     * Sets the relative funnel or pyramid width compared to the plot area.
     * 
     * @param width
     *            the width in percentage of the plot area
     * @see #setWidth(Number)
     */
    public void setWidthAsPercentage(Number width) {
        this.width = width + "%";
    }

    /**
     * @see #setDepth(Number)
     * @return depth
     */
    public Number getDepth() {
        return depth;
    }

    /**
     * The thickness of a 3D chart. Defaults to 0.
     * 
     * @param depth
     */
    public void setDepth(Number depth) {
        this.depth = depth;
    }

    /**
     * @see #setAllowPointSelect(Boolean)
     * @return allowPointSelect
     */
    public Boolean getAllowPointSelect() {
        return allowPointSelect;
    }

    /**
     * Allow this series' points to be selected by clicking on the markers, bars
     * or pie slices. Defaults to false.
     * 
     * @param allowPointSelect
     */
    public void setAllowPointSelect(Boolean allowPointSelect) {
        this.allowPointSelect = allowPointSelect;
    }

    /**
     * @see #setBorderColor(SolidColor)
     * @return borderColor
     */
    public SolidColor getBorderColor() {
        return borderColor;
    }

    /**
     * The color of the border surrounding each slice. Defaults to #FFFFFF.
     * 
     * @param borderColor
     */
    public void setBorderColor(SolidColor borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * @see #setBorderWidth(Number)
     * @return borderWidth
     */
    public Number getBorderWidth() {
        return borderWidth;
    }

    /**
     * The width of the border surrounding each slice. Defaults to 1.
     * 
     * @param borderWidth
     */
    public void setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * @see #setCenter(Number, Number)
     * @see #setCenter(String, String)
     * @return The center of the chart
     */
    public Object[] getCenter() {
        return center;
    }

    /**
     * Sets the center of the chart relative to the plot area. Can be
     * percentages or pixel values. Defaults to "50%", "50%".
     * 
     * @param left
     *            The left offset in percentages (String)
     * @param top
     *            The top offset in percentages (String)
     */
    public void setCenter(String left, String top) {
        Object[] array = null;
        if (left != null && top != null) {
            array = new Object[] { left, top };
        }
        center = array;
    }

    /**
     * Sets the center of the chart relative to the plot area. Can be
     * percentages or pixel values. Defaults to "50%", "50%".
     * 
     * @param left
     *            The left offset in pixels (Number)
     * @param top
     *            The top offset in pixels (Number)
     */
    public void setCenter(Number left, Number top) {
        Object[] array = null;
        if (left != null && top != null) {
            array = new Object[] { left, top };
        }
        center = array;
    }

    /**
     * @see #setHeight(Number)
     * @see #setHeightAsPercentage(Number)
     * @return the height of the funnel or pyramid, value can be String
     *         (percentage of the plot value) or Number (pixel value)
     */
    public Serializable getHeight() {
        return height;
    }

    /**
     * Sets the height of the funnel or pyramid in pixels
     * 
     * @param height
     *            the pixel height of the funnel chart
     * @see #setHeightAsPercentage(Number)
     */
    public void setHeight(Number height) {
        this.height = height;
    }

    /**
     * Sets the relative funnel or pyramid height compared to the plot area.
     * 
     * @param height
     *            the height in percentage of the plot area
     * @see #setHeight(Number)
     */
    public void setHeightAsPercentage(Number height) {
        this.height = height + "%";
    }

    /**
     * @see #setMinSize(Number)
     * @return minSize
     */
    public Number getMinSize() {
        return minSize;
    }

    /**
     * The minimum size for a pie in response to auto margins. The pie will try
     * to shrink to make room for data labels in side the plot area, but only to
     * this size. Defaults to 80.
     * 
     * @param minSize
     */
    public void setMinSize(Number minSize) {
        this.minSize = minSize;
    }

    /**
     * @see #setReversed(Boolean)
     * @return reversed
     */
    public Boolean getReversed() {
        return reversed;
    }

    /**
     * The pyramid is reversed by default, as opposed to the funnel, which
     * shares the layout engine, and is not reversed.
     * 
     * @param reversed
     */
    public void setReversed(Boolean reversed) {
        this.reversed = reversed;
    }

    /**
     * @see #setShadow(Boolean)
     * @return shadow
     */
    public Boolean getShadow() {
        return shadow;
    }

    /**
     * Whether to apply a drop shadow to the graph line. Defaults to false.
     * 
     * @param shadow
     */
    public void setShadow(Boolean shadow) {
        this.shadow = shadow;
    }

    /**
     * @see #setSlicedOffset(Number)
     * @return slicedOffset
     */
    public Number getSlicedOffset() {
        return slicedOffset;
    }

    /**
     * If a point is sliced, moved out from the center, how many pixels should
     * it be moved?. Defaults to 10.
     * 
     * @param slicedOffset
     */
    public void setSlicedOffset(Number slicedOffset) {
        this.slicedOffset = slicedOffset;
    }

    /**
     * @see #setVisible(Boolean)
     * @return visible
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * Set the initial visibility of the series. Defaults to true.
     * 
     * @param visible
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

}
