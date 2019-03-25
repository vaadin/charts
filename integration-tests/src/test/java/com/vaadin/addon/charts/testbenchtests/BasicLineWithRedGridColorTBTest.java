package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.lineandscatter.BasicLineWithRedGridLines;

public class BasicLineWithRedGridColorTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        return BasicLineWithRedGridLines.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
