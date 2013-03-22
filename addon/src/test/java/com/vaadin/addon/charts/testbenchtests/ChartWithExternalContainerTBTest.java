package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.container.ContainerSeriesWithSpline;


public class ChartWithExternalContainerTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return ContainerSeriesWithSpline.class.getName();
    }
    
}