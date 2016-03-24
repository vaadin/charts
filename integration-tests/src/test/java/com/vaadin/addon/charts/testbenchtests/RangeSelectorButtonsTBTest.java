package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.timeline.RangeSelectorButtons;

public class RangeSelectorButtonsTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return RangeSelectorButtons.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "timeline";
    }
}
