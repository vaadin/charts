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

import java.io.Serializable;

import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.Series;

/**
 * Handler interface for chart's drilldown callbacks.
 * <p/>
 * DrilldownCallback is used for async drilldown.
 * <p/>
 * {@link DrilldownCallback#handleDrilldown(DrilldownEvent)} is called when a
 * point with drilldown enabled is clicked and needs to return the Series to be
 * used as drilldown for the point.
 * <p/>
 * To enable async drilldown for a series item use
 * {@link DataSeries#addItemWithDrilldown(com.vaadin.addon.charts.model.DataSeriesItem)}
 */
public interface DrilldownCallback extends Serializable {

    /**
     * Method called when a point with drilldown enabled is clicked and should
     * return the Series to be used as drilldown for the point.
     * 
     * @param event
     * @return a {@link Series} instance to be used as drilldown for the point
     *         or <code>null</code> if nothing should be done
     */
    public Series handleDrilldown(DrilldownEvent event);

}
