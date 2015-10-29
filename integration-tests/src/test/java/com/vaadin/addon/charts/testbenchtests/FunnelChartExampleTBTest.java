package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.other.FunnelChartExample;

@Ignore("Theme axes styles are not set correctly")
public class FunnelChartExampleTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return FunnelChartExample.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }

}
