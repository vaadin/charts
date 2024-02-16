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
 * Listener interface for series hide events
 */
@FunctionalInterface
public interface SeriesHideListener extends SerializableEventListener {

    /**
     * Called when a series is hidden
     *
     * @param event
     *            the {@link SeriesHideEvent} containing information on the
     *            event.
     */
    public void onHide(SeriesHideEvent event);

}
