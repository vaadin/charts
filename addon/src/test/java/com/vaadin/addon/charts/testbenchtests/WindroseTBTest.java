package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.other.WindRose;

public class WindroseTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return WindRose.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}