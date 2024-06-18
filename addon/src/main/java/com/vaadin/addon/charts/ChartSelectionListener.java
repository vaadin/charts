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
 * Listener interface for chart selection events
 */
public interface ChartSelectionListener extends Serializable {

    /**
     * Called when the user finishes the selection of an area on the X axis.
     * 
     * @param event
     *            the {@link ChartSelectionEvent} containing information on the
     *            selection.
     */
    public void onSelection(ChartSelectionEvent event);

}
