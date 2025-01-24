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

import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Series;

/**
 * The DrilldownEvent class stores information on click events in drilldown
 * points
 */
public class DrilldownEvent implements Serializable {

    private final Series series;
    private final DataSeriesItem item;
    private final int itemIndex;

    /**
     * Construct a ChartDrilldownEvent
     * 
     * @param source
     * @param series
     * @param details
     */
    public DrilldownEvent(Chart source, Series series, DataSeriesItem item,
            int itemIndex) {
        this.series = series;
        this.item = item;
        this.itemIndex = itemIndex;
    }

    /**
     * Returns the {@link #getItem()} series.
     * 
     * @return
     */
    public Series getSeries() {
        return series;
    }

    /**
     * Returns the item that was clicked
     * 
     * @return
     */
    public DataSeriesItem getItem() {
        return item;
    }

    /**
     * Returns the index of {@link #getItem()} in {@link #getSeries()}.
     * 
     * @return
     */
    public int getItemIndex() {
        return itemIndex;
    }

}
