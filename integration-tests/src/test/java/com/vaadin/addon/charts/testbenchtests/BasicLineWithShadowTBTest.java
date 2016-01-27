package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.lineandscatter.BasicLineWithShadow;

public class BasicLineWithShadowTBTest extends AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        return BasicLineWithShadow.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
