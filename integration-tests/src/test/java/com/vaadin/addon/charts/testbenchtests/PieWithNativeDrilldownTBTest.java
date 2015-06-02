package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.pie.PieWithNativeDrilldown;

public class PieWithNativeDrilldownTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return PieWithNativeDrilldown.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "pie";
    }

}
