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

import java.io.Serializable;

/**
 * Listener interface for series hide events
 */
public interface SeriesHideListener extends Serializable {

    /**
     * Called when a series is hidden
     *
     * @param event
     *            the {@link SeriesHideEvent} containing information on the
     *            event.
     */
    public void onHide(SeriesHideEvent event);

}
