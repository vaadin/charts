package com.vaadin.addon.charts.events;

/*-
 * #%L
 * Vaadin Charts Addon
 * %%
 * Copyright (C) 2012 - 2019 Vaadin Ltd
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
