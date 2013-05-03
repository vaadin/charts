package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.pie.PieWithLegend;

public class PieWithLegendTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return PieWithLegend.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "pie";
    }
}
