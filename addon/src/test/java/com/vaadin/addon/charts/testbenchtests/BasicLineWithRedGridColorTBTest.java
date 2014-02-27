package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.lineandscatter.BasicLineWithRedGridLines;

public class BasicLineWithRedGridColorTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        String simpleName = BasicLineWithRedGridLines.class.getSimpleName();
        return simpleName;
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
