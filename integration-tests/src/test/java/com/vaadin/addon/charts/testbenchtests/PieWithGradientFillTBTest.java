package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.pie.PieWithGradientFill;

public class PieWithGradientFillTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return PieWithGradientFill.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "pie";
    }
}
