package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.HeatMapExample;

/**
 * Test for {@link HeatMapExample}
 */
public class HeatMapTBTest extends AbstractSimpleScreenShotTestBenchTest {
    @Override
    protected String getTestViewName() {
        return HeatMapExample.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
