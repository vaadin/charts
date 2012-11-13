package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.other.PolarChart;

public class PolarChartTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return PolarChart.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}