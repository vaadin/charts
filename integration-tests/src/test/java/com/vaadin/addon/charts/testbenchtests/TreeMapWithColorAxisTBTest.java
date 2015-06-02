package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.TreeMapWithColorAxis;

public class TreeMapWithColorAxisTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return TreeMapWithColorAxis.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
