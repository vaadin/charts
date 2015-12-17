package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.AngularGauge;

public class AngularGaugeTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return AngularGauge.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
