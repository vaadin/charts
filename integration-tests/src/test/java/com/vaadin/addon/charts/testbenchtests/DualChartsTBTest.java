package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.combinations.DualCharts;

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
