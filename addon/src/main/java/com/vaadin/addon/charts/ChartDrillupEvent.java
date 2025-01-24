/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts;

import com.vaadin.ui.Component;

/**
 * ChartDrillupEvent triggered when the 'Back to previous series' button is
 * clicked
 */
public class ChartDrillupEvent extends com.vaadin.ui.Component.Event {

    private static final long serialVersionUID = -2539790491959770326L;

    public ChartDrillupEvent(Component source) {
        super(source);
    }

}
