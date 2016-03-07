package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.declarative.DeclarativeTreemapWithLevels;

public class DeclarativeTreemapWithLevelsTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return DeclarativeTreemapWithLevels.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "declarative";
    }

}
