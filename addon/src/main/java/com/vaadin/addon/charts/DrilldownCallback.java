package com.vaadin.addon.charts;

import java.io.Serializable;

import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.Series;

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
