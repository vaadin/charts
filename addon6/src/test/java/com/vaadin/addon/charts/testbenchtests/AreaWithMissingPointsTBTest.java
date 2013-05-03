package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.area.AreaWithMissingPoints;

public class AreaWithMissingPointsTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return AreaWithMissingPoints.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "area";
    }

}
