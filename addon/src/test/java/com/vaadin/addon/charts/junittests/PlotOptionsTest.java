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
package com.vaadin.addon.charts.junittests;

import com.vaadin.addon.charts.model.PlotOptionsBubble;
import com.vaadin.server.Sizeable;
import org.junit.Test;

public class PlotOptionsTest {
    @Test(expected = IllegalArgumentException.class)
    public void plotOptions_setSizeWithCMUnit_raiseException() {
        PlotOptionsBubble plotOptions= new PlotOptionsBubble();
        plotOptions.setMaxSize(10, Sizeable.Unit.CM);

    }
}
