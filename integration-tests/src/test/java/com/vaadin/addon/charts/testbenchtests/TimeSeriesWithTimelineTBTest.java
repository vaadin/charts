package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.timeline.TimeSeriesWithTimeline;

public class TimeSeriesWithTimelineTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        String simpleName = TimeSeriesWithTimeline.class.getSimpleName();
        return simpleName;
    }

    @Override
    protected String getPackageName() {
        return "timeline";
    }

}
