package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.lineandscatter.BasicLine;

public class BasicLineTBTest extends AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        return BasicLine.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
