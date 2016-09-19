package com.vaadin.v7.addon.charts.events;

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

import com.vaadin.v7.addon.charts.model.Series;
import com.vaadin.v7.addon.charts.model.DataSeriesItem;

/**
 * Event for updating existing data series.
 * 
 * @since 2.0
 *
 */
public class DataUpdatedEvent extends AbstractSeriesItemEvent {

    private static final long serialVersionUID = 20141117;

    private final int pointIndex;

    /**
     * Constructs the event with given series, number and point index.
     * 
     * @param series
     *            Series.
     * @param value
     *            Value.
     * @param pointIndex
     *            Point index.
     */
    public DataUpdatedEvent(Series series, Number value, int pointIndex) {
        super(series, value);
        this.pointIndex = pointIndex;
    }

    /**
     * Constructs the event with given series, item and point index.
     * 
     * @param series
     *            Series.
     * @param item
     *            Series item.
     * @param pointIndex
     *            Point index.
     */
    public DataUpdatedEvent(Series series, DataSeriesItem item, int pointIndex) {
        super(series, item);
        this.pointIndex = pointIndex;
    }

    /**
     * Returns the point index.
     * 
     * @return Point index.
     */
    public int getPointIndex() {
        return pointIndex;
    }
}
