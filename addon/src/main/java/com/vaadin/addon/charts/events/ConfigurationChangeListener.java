/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.events;

import com.vaadin.addon.charts.model.Series;
import com.vaadin.event.SerializableEventListener;

/**
 * Listener interface for events triggered in Configuration. E.g. in DataSeries,
 * events like data add/remove/update.
 * 
 * @since 2.0
 * 
 */
public interface ConfigurationChangeListener extends SerializableEventListener {
    /**
     * A data point has been added
     *
     * @param event
     *            The event.
     */
    void dataAdded(DataAddedEvent event);

    /**
     * A drilldown series has been added
     * 
     * @param seriesIndex
     * @param pointIndex
     * @param series
     */
    void drilldownAdded(int seriesIndex, int pointIndex, Series series);

    /**
     * A data point has been removed
     * 
     * @param event
     *            The event.
     */
    void dataRemoved(DataRemovedEvent event);

    /**
     * A data point has been updated
     * 
     * @param event
     *            The event.
     */
    void dataUpdated(DataUpdatedEvent event);

    /**
     * The series is enabled or disabled
     * 
     * @param event
     *            The event.
     */
    void seriesStateChanged(SeriesStateEvent event);

    /**
     * State of animation has been changed.
     * 
     * @param animation
     *            Whether or not to use animation.
     */
    void animationChanged(boolean animation);

    /**
     * An axis has been rescaled.
     * 
     * @param event
     *            The event.
     */
    void axisRescaled(AxisRescaledEvent event);

    /**
     * A point has been sliced
     * 
     * @param event
     *            The event
     */
    void itemSliced(ItemSlicedEvent event);

    /**
     * The series has been changed.
     * 
     * @param event
     *            The event
     */
    void seriesChanged(SeriesChangedEvent event);

    /**
     * Reset zoom level by setting axis extremes to null
     * 
     * @param redraw
     * @param animate
     */
    void resetZoom(boolean redraw, boolean animate);
}
