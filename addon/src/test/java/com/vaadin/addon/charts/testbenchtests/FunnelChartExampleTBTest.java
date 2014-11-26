package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.other.FunnelChartExample;

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
