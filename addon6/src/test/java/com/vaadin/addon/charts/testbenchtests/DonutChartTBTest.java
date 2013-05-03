package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.pie.DonutChart;

public class DonutChartTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return DonutChart.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "pie";
    }
}