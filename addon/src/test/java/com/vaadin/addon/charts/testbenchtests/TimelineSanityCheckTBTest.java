package com.vaadin.addon.charts.testbenchtests;


public class TimelineSanityCheckTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    protected void startBrowser() {
        super.startBrowser();
        // We need a bigger viewport to fit everything.
        testBench.resizeViewPortTo(1212, 605);
    }

    @Override
    protected String getTestViewName() {
        return "SanityCheck";
    }

    @Override
    protected String getPackageName() {
        return "timeline";
    }

}
