/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts;

import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.shared.MouseEventDetails;
import com.vaadin.addon.charts.shared.MouseEventDetails.MouseButton;
import com.vaadin.addon.charts.util.Util;

/**
 * The PointClickEvent class stores data for click events on the points of the
 * chart.
 */
public class PointClickEvent extends AbstractPointEvent {

    private MouseEventDetails details;

    /**
     * Construct a PointClickEvent
     * 
     * @param source
     * @param details
     * @param series
     * @param category
     * @param pointIndex
     */
    public PointClickEvent(Chart source, MouseEventDetails details,
            Series series, String category, int pointIndex) {
        super(source, series, category, pointIndex);
        this.details = details;
    }

    /**
     * Gets the X coordinate of the point that was clicked.
     * <p>
     * Note, that if the axis type is Date, the value is "unix timestamp" which
     * is shifted to UTF time zone that is used by the client side
     * implementation. If you have used Date object as value, you most likely
     * want to pass the value thru {@link Util#toServerDate(double)} method
     * before actually using the value.
     * <p>
     * This value might be zero in a chart without axes like
     * {@link ChartType#PIE} or {@link ChartType#TREEMAP}
     * 
     * @return the X coordinate of the point that was clicked.
     */
    public double getX() {
        return details.getxValue();
    }

    /**
     * Gets the Y coordinate of the point that was clicked.
     * <p>
     * This value might be zero in a chart without axes like
     * {@link ChartType#PIE} or {@link ChartType#TREEMAP}
     * 
     * @return the Y coordinate of the point that was clicked.
     */
    public double getY() {
        return details.getyValue();
    }

    /**
     * @return the absolute x position of the clicked point in browser client
     *         area in pixels
     */
    public int getAbsoluteX() {
        return details.getAbsoluteX();
    }

    /**
     * @return the absolute y position of the clicked point in browser client
     *         area in pixels
     */
    public int getAbsoluteY() {
        return details.getAbsoluteY();
    }

    public MouseButton getButton() {
        return details.getButton();
    }

    /**
     * Checks if the Alt key was down when the mouse event took place.
     * 
     * @return true if Alt was down when the event occured, false otherwise
     */
    public boolean isAltKey() {
        return details.isAltKey();
    }

    /**
     * Checks if the Ctrl key was down when the mouse event took place.
     * 
     * @return true if Ctrl was pressed when the event occured, false otherwise
     */
    public boolean isCtrlKey() {
        return details.isCtrlKey();
    }

    /**
     * Checks if the Meta key was down when the mouse event took place.
     * 
     * @return true if Meta was pressed when the event occured, false otherwise
     */
    public boolean isMetaKey() {
        return details.isMetaKey();
    }

    /**
     * Checks if the Shift key was down when the mouse event took place.
     * 
     * @return true if Shift was pressed when the event occured, false otherwise
     */
    public boolean isShiftKey() {
        return details.isShiftKey();
    }

}
