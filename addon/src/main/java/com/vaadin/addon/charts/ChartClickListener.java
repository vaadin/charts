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
 * Listener interface for click events on the chart's area
 */
@FunctionalInterface
public interface ChartClickListener extends SerializableEventListener {

    /**
     * Called when the user clicks somewhere on the chart.
     * 
     * @param event
     *            the {@link ChartClickEvent} containing information on the
     *            click.
     */
    public void onClick(ChartClickEvent event);

}
