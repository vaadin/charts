package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.container.VsevenChartWithExternalContainer;

public class VsevenChartWithExternalContainerTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return VsevenChartWithExternalContainer.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "container";
    }
}
