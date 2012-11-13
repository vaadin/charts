package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.area.PercentageArea;

public class PercentageAreaTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return PercentageArea.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "area";
    }
}
