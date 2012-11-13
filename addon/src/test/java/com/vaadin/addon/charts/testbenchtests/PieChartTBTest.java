package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.pie.PieChart;

public class PieChartTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return PieChart.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "pie";
    }
}
