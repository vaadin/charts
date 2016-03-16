package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.threed.ThreeDAxisConfiguration;

public class ThreeDAxisConfigurationTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return ThreeDAxisConfiguration.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "threed";
    }
}
