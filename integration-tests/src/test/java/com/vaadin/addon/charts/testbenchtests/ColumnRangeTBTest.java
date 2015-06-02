package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.ColumnRange;

public class ColumnRangeTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return ColumnRange.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
