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

import com.vaadin.event.SerializableEventListener;

/**
 * Listener interface for chart selection events
 */
@FunctionalInterface
public interface ChartSelectionListener extends SerializableEventListener {

    /**
     * Called when the user finishes the selection of an area on the X axis.
     * 
     * @param event
     *            the {@link ChartSelectionEvent} containing information on the
     *            selection.
     */
    public void onSelection(ChartSelectionEvent event);

}
