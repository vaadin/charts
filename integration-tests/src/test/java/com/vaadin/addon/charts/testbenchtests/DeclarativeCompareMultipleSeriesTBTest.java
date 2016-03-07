package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.declarative.DeclarativeCompareMultipleSeries;

public class DeclarativeCompareMultipleSeriesTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return DeclarativeCompareMultipleSeries.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "declarative";
    }

}
