package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.columnandbar.BasicColumn;

public class BasicColumnTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return BasicColumn.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "columnandbar";
    }
}