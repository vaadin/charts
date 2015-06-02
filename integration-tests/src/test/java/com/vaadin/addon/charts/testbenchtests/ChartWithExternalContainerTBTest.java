package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.container.ChartWithExternalContainer;

public class ChartWithExternalContainerTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return ChartWithExternalContainer.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "container";
    }
}
