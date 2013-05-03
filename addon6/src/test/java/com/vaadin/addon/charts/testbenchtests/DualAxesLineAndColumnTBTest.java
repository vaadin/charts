package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.columnandbar.DualAxesLineAndColumn;

public class DualAxesLineAndColumnTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return DualAxesLineAndColumn.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "columnandbar";
    }

}
