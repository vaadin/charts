/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts;

import java.io.Serializable;

/**
 * Listener interface for X axes extremes changed events
 */
public interface XAxesExtremesChangeListener extends Serializable {

    /**
     * Called when a X axis extremes has changed
     *
     * @param event
     *            the {@link XAxesExtremesChangeEvent} containing information on the
     *            event.
     */
    public void onXAxesExtremesChange(XAxesExtremesChangeEvent event);

}
