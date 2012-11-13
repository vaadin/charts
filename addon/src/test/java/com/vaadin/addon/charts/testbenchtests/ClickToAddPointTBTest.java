package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.dynamic.ClickToAddPoint;

public class ClickToAddPointTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        String simpleName = ClickToAddPoint.class.getSimpleName();
        return simpleName;
    }

    @Override
    protected String getPackageName() {
        return "dynamic";
    }
}
