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
 * Listener interface for legend item click events
 */
public interface LegendItemClickListener extends Serializable {

    /**
     * Called when the user click an item in the chart's legend
     * 
     * @param event
     *            the {@link LegendItemClickEvent} containing the name of the
     *            series which legend item was clicked
     */
    public void onClick(LegendItemClickEvent event);

}
