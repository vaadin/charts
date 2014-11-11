package com.vaadin.addon.charts;

import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.util.Util;

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
 * The PointClickEvent class stores data for click events on the points of the
 * chart.
 */
public class PointClickEvent extends com.vaadin.ui.Component.Event {

    private final Double x;
    private final Double y;
    private final String category;
    private final Series series;
    private int pointIndex;
    private final int absoluteX;
    private final int absoluteY;

    /**
     * Construct a PointClickEvent
     * 
     * @param source
     * @param x
     * @param y
     * @param series
     * @param category
     * @param pointIndex
     */
    public PointClickEvent(Chart source, double x, double y, Series series,
            String category, int pointIndex, int absoluteX, int absoluteY) {
        super(source);
        this.x = x;
        this.y = y;
        this.series = series;
        this.category = category;
        this.pointIndex = pointIndex;
        this.absoluteX = absoluteX;
        this.absoluteY = absoluteY;
    }

    /**
     * Gets the X coordinate of the point that was clicked.
     * <p>
     * Note, that if the axis type is Date, the value is "unix timestamp" which
     * is shifted to UTF time zone that is used by the client side
     * implementation. If you have used Date object as value, you most likely
     * want to pass the value thru {@link Util#toServerDate(double)} method
     * before actually using the value.
     * 
     * @return the X coordinate of the point that was clicked.
     */
    public double getX() {
        return x;
    }

    /**
     * @return the Y coordinate of the point that was clicked.
     */
    public double getY() {
        return y;
    }

    /**
     * @return the series containing the point that was clicked
     */
    public Series getSeries() {
        return series;
    }

    /**
     * @return the name of the category for the point that was clicked.
     */
    public String getCategory() {
        return category;
    }

    /**
     * @return the index of the point in its series that was clicked
     */
    public int getPointIndex() {
        return pointIndex;
    }

    /**
     * @return the absolute x position of the clicked point in browser client
     *         area in pixels
     */
    public int getAbsoluteX() {
        return absoluteX;
    }

    /**
     * @return the absolute y position of the clicked point in browser client
     *         area in pixels
     */
    public int getAbsoluteY() {
        return absoluteY;
    }

}
