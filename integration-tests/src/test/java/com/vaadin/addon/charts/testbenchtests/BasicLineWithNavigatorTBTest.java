package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.lineandscatter.BasicLineWithNavigator;

public class BasicLineWithNavigatorTBTest extends AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        String simpleName = BasicLineWithNavigator.class.getSimpleName();
        return simpleName;
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
