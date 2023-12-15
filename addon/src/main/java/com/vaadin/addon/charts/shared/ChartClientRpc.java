/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.shared;

import com.vaadin.shared.communication.ClientRpc;

public interface ChartClientRpc extends ClientRpc {

    /**
     * Adds a new point into the series identified by seriesIndex
     * 
     * @param pointJson
     * @param seriesIndex
     * @param shift
     */
    void addPoint(String pointJson, int seriesIndex, boolean shift);

    /**
     * Removes the first point found at the given index
     * 
     * @param pointIndex
     * @param seriesIndex
     */
    void removePoint(int pointIndex, int seriesIndex);

    /**
     * Updates the value of a point in the series identified by seriesIndex
     * 
     * @param seriesIndex
     * @param pointIndex
     * @param newValue
     */
    void updatePointValue(int seriesIndex, int pointIndex, double newValue);

    /**
     * Disables or enables series with given seriesName
     */
    void setSeriesEnabled(int seriesIndex, boolean enabled);

    void setAnimationEnabled(boolean animation);

    void updatePoint(int indexOf, int pointIndex, String json);

    public static final short X_AXIS = 0;
    public static final short Y_AXIS = 1;
    public static final short Z_AXIS = 2;
    public static final short COLOR_AXIS = 3;

    void rescaleAxis(int axisCategory, int axisIndex, double minimum,
            double maximum, boolean redraw, boolean animate);

    void sliceItem(int seriesIndex, int pointIndex, boolean sliced,
            boolean redraw, boolean animation);

    void addDrilldown(String string, int seriesIndex, int pointIndex);

    void resetZoom(boolean redraw, boolean animate);

}
