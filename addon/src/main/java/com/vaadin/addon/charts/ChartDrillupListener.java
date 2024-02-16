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
 * Listener interface for drillup events on the chart
 */
@FunctionalInterface
public interface ChartDrillupListener extends SerializableEventListener {

    /**
     * Called when the user clicks the 'Back to previous series' button.
     * 
     * @param event
     */
    public void onDrillup(ChartDrillupEvent event);

}
