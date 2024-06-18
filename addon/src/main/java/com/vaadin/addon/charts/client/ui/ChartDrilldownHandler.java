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
package com.vaadin.addon.charts.client.ui;

/**
 * Client side ChartDrilldownHandler
 */
public interface ChartDrilldownHandler {
    void onDrilldown(ChartDrilldownEvent event);

    void onDrillup();

}
