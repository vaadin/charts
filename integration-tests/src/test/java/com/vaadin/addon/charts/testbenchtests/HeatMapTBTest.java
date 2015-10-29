package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.other.HeatMapExample;

/**
 * Test for {@link HeatMapExample}
 */
@Ignore("config.getColorAxis missing")
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
