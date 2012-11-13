package com.vaadin.addon.charts.testbenchtests;

public class TimeDataWithIrregularIntervalsTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        String simpleName = TimeDataWithIrregularIntervalsTBTest.class
                .getSimpleName();
        return simpleName;
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }
}
