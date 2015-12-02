package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.LegendMarginTestUI;

public class LegendMarginTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return LegendMarginTestUI.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
