package com.vaadin.addon.charts.model;

import java.io.Serializable;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <http://vaadin.com/license/cval-3.0>.
 * #L%
 */

/**
 * Options for Pyramid chart. These same options are used as a base class for
 * Funnel chart options.
 * 
 * @author miki
 *
 */
public class PlotOptionsPyramid extends AbstractLinePlotOptions {

    private Serializable width;

    public PlotOptionsPyramid() {
	super();
    }

    @Override
    public ChartType getChartType() {
	return ChartType.PYRAMID;
    }

    /**
     * @return the width of the funnel, value can be String (percentage of the
     *         plot value) or Number (pixel value)
     */
    public Serializable getWidth() {
	return width;
    }

    /**
     * Sets the width of the funnel in pixels
     * 
     * @param width
     *            the pixel width of the funnel chart
     * @see #setWidthPercentage(Number)
     */
    public void setWidth(Number width) {
	this.width = width;
    }

    /**
     * Sets the relative funnel width compared to the plot area.
     * 
     * @param width
     *            the width in percentage of the plot area
     * @see #setWidth(Number)
     */
    public void setWidthPercentage(Number width) {
	this.width = width + "%";
    }

}
