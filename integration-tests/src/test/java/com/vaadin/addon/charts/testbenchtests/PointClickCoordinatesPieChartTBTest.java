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
package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.pointclickevent.PointClickCoordinatesPieChart;

@Ignore("Absolute coordinates affected by Theme change")
public class PointClickCoordinatesPieChartTBTest
        extends AbstractPointClickCoordinatesTest {

    @Override
    protected String getTestViewName() {
        return PointClickCoordinatesPieChart.class.getSimpleName();
    }

}
