package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.other.Clock;

public class ClockTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return Clock.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
    
    @Override
    protected int getScreenShotDelay() {
        return 8000;
    }
}