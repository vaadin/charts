package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.lineandscatter.BasicLineZIndex;

public class BasicLineZIndexTBTest
        extends AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        return BasicLineZIndex.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
