package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.themes.GrayThemedAreaWithNegativeValues;

public class GrayThemedAreaWithNegativeValuesTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return GrayThemedAreaWithNegativeValues.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "themes";
    }
}
