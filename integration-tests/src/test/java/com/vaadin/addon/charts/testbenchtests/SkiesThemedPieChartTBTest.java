package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.themes.SkiesThemedPieChart;

public class SkiesThemedPieChartTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return SkiesThemedPieChart.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "themes";
    }
}
