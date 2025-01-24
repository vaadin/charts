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
package com.vaadin.addon.charts.model.junittests;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.addon.charts.model.AbstractSeries;

public class SeriesTest {

    @Test
    public void seriesVisibility_defaultVisibility_trueByDefault() {
        @SuppressWarnings("serial")
        AbstractSeries dataSeries = new AbstractSeries() {
        };

        Assert.assertTrue(dataSeries.isVisible());
    }
}
