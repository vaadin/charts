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
package com.vaadin.addon.charts;

import com.vaadin.event.SerializableEventListener;

/**
 * Listener interface for select events on the data points of the chart
 */
@FunctionalInterface
public interface PointSelectListener extends SerializableEventListener {
    /**
     * Called when a data point is selected
     *
     * @param event a {@link PointSelectEvent} containing information on the selected datapoint
     */
    public void onSelect(PointSelectEvent event);

}
