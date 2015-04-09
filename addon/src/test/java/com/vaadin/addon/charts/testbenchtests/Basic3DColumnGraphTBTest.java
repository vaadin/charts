package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.threed.Basic3DColumn;

public class Basic3DColumnGraphTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return Basic3DColumn.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "threed";
    }
}