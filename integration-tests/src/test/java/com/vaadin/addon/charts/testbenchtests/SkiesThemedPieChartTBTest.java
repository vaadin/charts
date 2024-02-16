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
package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.themes.SkiesThemedPieChart;

@Ignore("test view uses an image that is pointing to an URL that no longer works")
public class SkiesThemedPieChartTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return SkiesThemedPieChart.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "themes";
    }
}
