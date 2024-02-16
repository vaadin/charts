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
 * Listener interface for X axes extremes changed events
 */
@FunctionalInterface
public interface XAxesExtremesChangeListener extends SerializableEventListener {

    /**
     * Called when a X axis extremes has changed
     *
     * @param event
     *            the {@link XAxesExtremesChangeEvent} containing information on the
     *            event.
     */
    public void onXAxesExtremesChange(XAxesExtremesChangeEvent event);

}
