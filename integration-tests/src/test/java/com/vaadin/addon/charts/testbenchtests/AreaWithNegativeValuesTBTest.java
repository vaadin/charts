package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.area.AreaWithNegativeValues;

public class AreaWithNegativeValuesTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return AreaWithNegativeValues.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "area";
    }
}
