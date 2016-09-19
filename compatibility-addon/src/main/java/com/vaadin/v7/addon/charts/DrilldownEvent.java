package com.vaadin.v7.addon.charts;

import java.io.Serializable;

import com.vaadin.v7.addon.charts.model.Series;
import com.vaadin.v7.addon.charts.model.DataSeriesItem;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
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
