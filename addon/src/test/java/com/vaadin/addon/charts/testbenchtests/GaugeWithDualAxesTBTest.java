package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.other.GaugeWithDualAxes;

public class GaugeWithDualAxesTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return GaugeWithDualAxes.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }

}