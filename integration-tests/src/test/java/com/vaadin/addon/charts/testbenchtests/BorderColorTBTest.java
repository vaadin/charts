package com.vaadin.addon.charts.testbenchtests;


import com.vaadin.addon.charts.examples.other.BorderColorTest;

public class BorderColorTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return BorderColorTest.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
