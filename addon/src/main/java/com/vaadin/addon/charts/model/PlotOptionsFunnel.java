package com.vaadin.addon.charts.model;

import java.io.Serializable;

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
 * Plot options that are specific for {@link ChartType#PIE} charts
 * 
 * @see AbstractPlotOptions
 * @see AbstractCommonPlotOptions
 */
public class PlotOptionsFunnel extends PlotOptionsPyramid {

    private Serializable neckWidth;
    private Serializable neckHeight;

    /**
     * @see #setNeckWidth(Number)
     * @return the neck width in pixels (Number) or percentage (String)
     */
    public Object getNeckWidth() {
        return neckWidth;
    }

    /**
     * Sets the width of the neck, the lower part of the funnel.
     * 
     * @param neckWidth
     *            the neck width to set in pixels
     */
    public void setNeckWidth(Number neckWidth) {
        this.neckWidth = neckWidth;
    }

    /**
     * Sets the width of the neck, the lower part of the funnel.
     * 
     * @param neckWidth
     *            the neck width to set in percentages of the plot area width
     */
    public void setNeckWidthPercentage(Number neckWidth) {
        this.neckWidth = neckWidth + "%";
    }

    /**
     * @return the neck height, value can be String (percentage) or Number
     *         (pixel value)
     */
    public Serializable getNeckHeight() {
        return neckHeight;
    }

    /**
     * Sets the height of the neck, the lower part of the funnel.
     * 
     * @param neckHeight
     *            the neck height to set in pixels
     */
    public void setNeckHeight(Number neckHeight) {
        this.neckHeight = neckHeight;
    }

    /**
     * Sets the height of the neck, the lower part of the funnel.
     * 
     * @param neckHeight
     *            the neck height to set in percentage of the plot area height
     */
    public void setNeckHeightPercentage(Number neckHeight) {
        this.neckHeight = neckHeight + "%";
    }

}
