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

import com.vaadin.event.SerializableEventListener;

/**
 * Listener interface for series show events
 */
@FunctionalInterface
public interface SeriesShowListener extends SerializableEventListener {

    /**
     * Called when a series is shown
     *
     * @param event
     *            the {@link SeriesShowEvent} containing information on the
     *            event.
     */
    public void onShow(SeriesShowEvent event);

}
