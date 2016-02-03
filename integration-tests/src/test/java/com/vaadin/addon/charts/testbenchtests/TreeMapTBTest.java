package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.TreemapWithLevels;

public class TreeMapTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return TreemapWithLevels.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
