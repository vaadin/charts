package com.vaadin.addon.charts;

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

import java.io.Serializable;

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
