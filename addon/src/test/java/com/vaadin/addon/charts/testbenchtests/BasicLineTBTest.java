package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.lineandscatter.BasicLine;

public class BasicLineTBTest extends AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        String simpleName = BasicLine.class.getSimpleName();
        return simpleName;
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
