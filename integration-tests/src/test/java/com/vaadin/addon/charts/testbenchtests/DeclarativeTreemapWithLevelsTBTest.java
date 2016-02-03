package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.declarative.TreemapWithLevels;

public class DeclarativeTreemapWithLevelsTBTest extends AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        String simpleName = TreemapWithLevels.class.getSimpleName();
        return simpleName;
    }

    @Override
    protected String getPackageName() {
        return "declarative";
    }

}
