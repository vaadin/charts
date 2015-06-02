package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.pie.PieWithStartAndEndAngle;

public class PieWithStartAndEndAngleTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return PieWithStartAndEndAngle.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "pie";
    }
}
