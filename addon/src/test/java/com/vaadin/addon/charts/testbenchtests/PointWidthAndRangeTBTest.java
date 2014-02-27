package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.columnandbar.BasicColumnWithPointWidthAndRange;

public class PointWidthAndRangeTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return BasicColumnWithPointWidthAndRange.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "columnandbar";
    }
}