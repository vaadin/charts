package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.columnandbar.ColumnWithDrilldown;

public class ColumnWithDrilldownTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    // TODO: better test

    protected String getTestViewName() {
        return ColumnWithDrilldown.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "columnandbar";
    }
}
