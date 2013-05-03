package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.themes.SkiesThemedBarChart;

public class SkiesThemedBarChartTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return SkiesThemedBarChart.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "themes";
    }
}
