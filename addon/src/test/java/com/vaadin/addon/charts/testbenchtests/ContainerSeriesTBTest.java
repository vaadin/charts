package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.container.ChartDynamicUpdateContainer;

public class ContainerSeriesTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return ChartDynamicUpdateContainer.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "container";
    }
}