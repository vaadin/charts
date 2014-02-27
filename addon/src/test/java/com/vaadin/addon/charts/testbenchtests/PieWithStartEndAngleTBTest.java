package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.pie.PieWithStartEndAngle;

public class PieWithStartEndAngleTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return PieWithStartEndAngle.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "pie";
    }
}
