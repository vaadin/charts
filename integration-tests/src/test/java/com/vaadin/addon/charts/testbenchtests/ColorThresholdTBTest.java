package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.ColorThreshold;

public class ColorThresholdTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return ColorThreshold.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }

}
