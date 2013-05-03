package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.timeline.SanityCheck;


public class TimelineSanityCheckTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    protected void startBrowser() {
        super.startBrowser();
        // We need a bigger viewport to fit everything.
        testBench.resizeViewPortTo(1212, 605);
    }

    @Override
    protected String getTestViewName() {
        return SanityCheck.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "timeline";
    }

}
