package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.other.WaterfallChartExample;

public class WaterfallChartExampleTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return WaterfallChartExample.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }

}
