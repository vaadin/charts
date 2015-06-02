package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.combinations.ScatterWithRegressionLine;

public class ScatterWithRegressionLineTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return ScatterWithRegressionLine.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "combinations";
    }

}
