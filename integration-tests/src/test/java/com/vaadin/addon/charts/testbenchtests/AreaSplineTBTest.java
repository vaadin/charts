package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.area.AreaSpline;

public class AreaSplineTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return AreaSpline.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "area";
    }

}
