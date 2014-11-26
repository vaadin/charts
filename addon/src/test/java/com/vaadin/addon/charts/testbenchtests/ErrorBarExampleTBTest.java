package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.other.ErrorBarExample;

public class ErrorBarExampleTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return ErrorBarExample.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }

}
