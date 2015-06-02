package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.BubbleChartExample;

public class BubbleChartExampleTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return BubbleChartExample.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
