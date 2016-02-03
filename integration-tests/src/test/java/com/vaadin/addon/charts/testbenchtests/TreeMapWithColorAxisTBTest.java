package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.TreemapWithColorAxis;

public class TreeMapWithColorAxisTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return TreemapWithColorAxis.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
