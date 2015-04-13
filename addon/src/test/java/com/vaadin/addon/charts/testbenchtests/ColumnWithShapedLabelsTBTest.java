package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.columnandbar.ColumnWithShapedLabels;

public class ColumnWithShapedLabelsTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return ColumnWithShapedLabels.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "columnandbar";
    }

}
