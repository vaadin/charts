package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.area.BasicArea;

public class BasicAreaTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return BasicArea.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "area";
    }
}
