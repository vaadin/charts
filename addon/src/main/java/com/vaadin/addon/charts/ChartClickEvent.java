package com.vaadin.addon.charts;

import com.vaadin.addon.charts.shared.MouseEventDetails;
import com.vaadin.addon.charts.shared.MouseEventDetails.MouseButton;
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
 * The ChartClickEvent class stores information on click events anywhere on the
 * area of the chart.
 */
public class ChartClickEvent extends com.vaadin.ui.Component.Event {

    private final MouseEventDetails details;

    /**
     * Construct a ChartClickEvent
     * 
     * @param source
     * @param details
     */
    public ChartClickEvent(Chart source, MouseEventDetails details) {
        super(source);
        this.details = details;
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
        return details.getxValue();
    }

    /**
     * @return the Y coordinate of the click
     */
    public double getyAxisValue() {
        return details.getyValue();
    }

    /**
     * @return the absolute x position of the clicked point in browser client
     *         area in pixels or -1 if chart type (like pie) don't have relevant
     *         point
     */
    public int getAbsoluteX() {
        return details.getAbsoluteX();
    }

    /**
     * @return the absolute x position of the clicked point in browser client
     *         area in pixels or -1 if chart type (like pie) don't have relevant
     *         point
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
