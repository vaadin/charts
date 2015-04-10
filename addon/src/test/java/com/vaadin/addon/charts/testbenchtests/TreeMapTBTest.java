package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.other.TreeMapWithLevels;

public class TreeMapTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return TreeMapWithLevels.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}