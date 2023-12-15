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
 * Listener interface for click events on the data points of the chart
 */
public interface PointClickListener extends Serializable {
    /**
     * Called when a data point is clicked by the user.
     * 
     * @param event
     *            a {@link PointClickEvent} containing information on the click
     */
    public void onClick(PointClickEvent event);

}
