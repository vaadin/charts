package com.vaadin.addon.charts;

import com.vaadin.addon.charts.util.Util;

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
 * The ChartClickEvent class stores information on click events anywhere on the
 * area of the chart.
 */
public class ChartClickEvent extends com.vaadin.ui.Component.Event {

    private final Double xAxisValue;
    private final Double yAxisValue;

    /**
     * Constructs a ChartClickEvent
     * 
     * @param source
     * @param xAxis
     * @param yAxis
     */
    public ChartClickEvent(Chart source, double xAxis, double yAxis) {
        super(source);
        xAxisValue = xAxis;
        yAxisValue = yAxis;
    }

    /**
     * Gets the x axis value of the clicked point.
     * <p>
     * Note, that if the axis type is Date, the value is "unix timestamp" which
     * is shifted to UTF time zone that is used by the client side
     * implementation. If you have used Date object as value, you most likely
     * want to pass the value thru {@link Util#toServerDate(double)} method
     * before actually using the value.
     * 
     * @return the X coordinate of the click.
     */
    public double getxAxisValue() {
        return xAxisValue;
    }

    /**
     * @return the Y coordinate of the click
     */
    public double getyAxisValue() {
        return yAxisValue;
    }

}
