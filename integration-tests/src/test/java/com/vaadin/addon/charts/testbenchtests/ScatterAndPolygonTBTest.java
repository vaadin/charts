package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.combinations.ScatterAndPolygon;

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
