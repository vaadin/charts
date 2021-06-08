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

import com.vaadin.event.SerializableEventListener;

/**
 * Listener interface for unselect events on the data points of the chart
 */
@FunctionalInterface
public interface PointUnselectListener extends SerializableEventListener {

    /**
     * Called when a data point is selected
     *
     * @param event a {@link PointUnselectEvent} containing information on the unselected datapoint
     */
    public void onUnselect(PointUnselectEvent event);

}
