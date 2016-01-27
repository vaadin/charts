package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.timeline.CompareMultipleSeries;

public class CompareMultipleSeriesTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return CompareMultipleSeries.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "timeline";
    }

}
