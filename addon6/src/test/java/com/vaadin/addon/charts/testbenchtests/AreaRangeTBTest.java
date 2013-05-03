package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.other.AreaRange;

public class AreaRangeTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return AreaRange.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}