package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;

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

/**
 * Plot options that are specific for {@link ChartType#BOXPLOT} charts
 * 
 * @see AbstractPlotOptions
 * @see AbstractLinePlotOptions
 */
public class PlotOptionsBoxPlot extends PlotOptionsErrorBar {

    @Override
    public ChartType getChartType() {
        return ChartType.BOXPLOT;
    }

    /**
     * @see #setMedianColor(Color)
     */
    public Color getMedianColor() {
        return medianColor;
    }

    /**
     * Sets the color of the median line. If null, the general series color
     * applies. Defaults to null.
     * 
     * @param medianColor
     *            the color for median line
     */
    public void setMedianColor(Color medianColor) {
        this.medianColor = medianColor;
    }

    /**
     * @see #setMedianWidth(Number)
     */
    public Number getMedianWidth() {
        return medianWidth;
    }

    /**
     * Sets the pixel width of the median line. If null, the lineWidth is used.
     * 
     * @param medianWidth the pixel width of median line
     */
    public void setMedianWidth(Number medianWidth) {
        this.medianWidth = medianWidth;
    }

    private Color medianColor;
    private Number medianWidth;


}
