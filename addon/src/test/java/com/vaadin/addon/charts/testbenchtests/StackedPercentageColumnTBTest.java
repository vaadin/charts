package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.columnandbar.StackedPercentageColumn;

public class StackedPercentageColumnTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return StackedPercentageColumn.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "columnandbar";
    }
}
