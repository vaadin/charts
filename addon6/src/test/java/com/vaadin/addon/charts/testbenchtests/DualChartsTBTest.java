package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.combinations.DualCharts;

public class DualChartsTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return DualCharts.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "combinations";
    }

}
