package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.timeline.SingleLineSeries;

public class SingleLineSeriesWithTimelineTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return SingleLineSeries.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "timeline";
    }

}
