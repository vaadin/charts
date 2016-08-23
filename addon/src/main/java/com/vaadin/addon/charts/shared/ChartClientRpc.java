package com.vaadin.addon.charts.shared;

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
