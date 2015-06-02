package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.combinations.ColumnLineAndPie;

public class ColumnLineAndPieTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return ColumnLineAndPie.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "combinations";
    }

}
