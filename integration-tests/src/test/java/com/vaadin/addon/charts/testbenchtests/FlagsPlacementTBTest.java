package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.timeline.FlagsPlacement;

public class FlagsPlacementTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return FlagsPlacement.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "timeline";
    }

}
