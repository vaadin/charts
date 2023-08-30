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
package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.pointclickevent.PointClickCoordinatesTreeMapChart;

@Ignore("Absolute coordinates affected by Theme change")
public class PointClickCoordinatesTreeMapChartTBTest
        extends AbstractPointClickCoordinatesTest {

    @Override
    protected String getTestViewName() {
        return PointClickCoordinatesTreeMapChart.class.getSimpleName();
    }

    @Override
    protected void assertChartClickEventCoordinates() {
        // All coordinates within TreeMap cause a PointClickEvent
    }
}
