package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.lineandscatter.LineWithDashSelector;

public class LineWithDashSelectorTBTest extends AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        return LineWithDashSelector.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
