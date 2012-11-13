package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.combinations.MultipleAxes;

public class MultipleAxesTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return MultipleAxes.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "combinations";
    }

}
