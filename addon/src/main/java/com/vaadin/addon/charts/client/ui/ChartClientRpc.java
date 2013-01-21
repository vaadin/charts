package com.vaadin.addon.charts.client.ui;

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

import com.vaadin.shared.communication.ClientRpc;

public interface ChartClientRpc extends ClientRpc {

    /**
     * Adds a new point into the series identified by seriesIndex
     * 
     * @param x
     * @param y
     * @param seriesIndex
     */
    void addPoint(double x, double y, int seriesIndex);

    /**
     * Removes the first point found at the given coordinates
     * 
     * @param x
     * @param y
     */
    void removePoint(double x, double y);

    /**
     * Updates the value of a point in the series identified by seriesIndex
     * 
     * @param seriesIndex
     * @param pointIndex
     * @param newValue
     */
    void updatePointValue(int seriesIndex, int pointIndex, double newValue);

    /**
     * Sets whether to redraw the chart after a point is altered. Default is
     * true.
     * 
     * @param redraw
     */
    void setRedrawAfterUpdate(boolean redraw);

    /**
     * When true, updates will be animated using the default animation options.
     * Default is true.
     * 
     * @param animationAfterUpdate
     */
    void setAnimationAfterUpdate(boolean animationAfterUpdate);

    /**
     * When shift is true, one point is shifted off the start of the series as
     * one is appended to the end. Use this option for live charts monitoring a
     * value over time. Default is false.
     * 
     * @param shiftAfterUpdate
     */
    void setShiftAfterUpdate(boolean shiftAfterUpdate);

    /**
     * Disables or enables series with given seriesName
     */
    void setSeriesEnabled(String seriesName, boolean enabled);

}
