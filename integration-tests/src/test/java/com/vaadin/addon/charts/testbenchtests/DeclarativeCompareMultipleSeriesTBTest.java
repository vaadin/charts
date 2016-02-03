package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.declarative.CompareMultipleSeries;

public class DeclarativeCompareMultipleSeriesTBTest extends AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        String simpleName = CompareMultipleSeries.class.getSimpleName();
        return simpleName;
    }

    @Override
    protected String getPackageName() {
        return "declarative";
    }

}
