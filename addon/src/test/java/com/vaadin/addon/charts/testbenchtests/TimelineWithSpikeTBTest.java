package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.timeline.TimelineWithSpike;

public class TimelineWithSpikeTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return TimelineWithSpike.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "timeline";
    }
}
