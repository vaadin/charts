package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.VUMeter;

public class VUMeterTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return VUMeter.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }

}
