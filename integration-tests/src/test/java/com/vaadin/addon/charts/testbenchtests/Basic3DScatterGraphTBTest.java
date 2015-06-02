package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.threed.Basic3DScatter;

public class Basic3DScatterGraphTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return Basic3DScatter.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "threed";
    }
}
