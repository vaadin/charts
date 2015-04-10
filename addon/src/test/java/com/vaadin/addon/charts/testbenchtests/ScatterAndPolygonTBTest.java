package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.combinations.ScatterAndPolygon;

public class ScatterAndPolygonTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return ScatterAndPolygon.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "combinations";
    }

}
