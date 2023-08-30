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
 * Listener interface for unselect events on the data points of the chart
 */
public interface PointUnselectListener extends Serializable {

    /**
     * Called when a data point is selected
     *
     * @param event a {@link PointUnselectEvent} containing information on the unselected datapoint
     */
    public void onUnselect(PointUnselectEvent event);

}
