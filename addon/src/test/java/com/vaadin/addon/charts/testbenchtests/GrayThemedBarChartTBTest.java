package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.themes.GrayThemedBarChart;

public class GrayThemedBarChartTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return GrayThemedBarChart.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "themes";
    }
}
