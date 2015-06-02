package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.SimpleSparklineExample;

public class SimpleSparklineTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return SimpleSparklineExample.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
