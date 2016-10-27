package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.columnandbar.BasicColumnWithPointWidthAndRange;

@Ignore("Overlapping components due to theme change")
public class PointWidthAndRangeTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return BasicColumnWithPointWidthAndRange.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "columnandbar";
    }
}
