package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.area.StackedArea;

public class StackedAreaTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return StackedArea.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "area";
    }
}