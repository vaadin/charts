package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.pie.PieWithLegendNavigator;

public class PieWithLegendNavigatorTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return PieWithLegendNavigator.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "pie";
    }
}
