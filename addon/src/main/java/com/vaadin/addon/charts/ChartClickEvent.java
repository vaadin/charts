package com.vaadin.addon.charts;

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
