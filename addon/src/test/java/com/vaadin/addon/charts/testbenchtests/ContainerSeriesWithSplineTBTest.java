package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.container.ContainerSeriesWithSpline;

public class ContainerSeriesWithSplineTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return ContainerSeriesWithSpline.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "container";
    }

}