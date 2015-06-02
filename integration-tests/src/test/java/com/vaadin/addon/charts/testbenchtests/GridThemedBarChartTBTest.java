package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.themes.GridThemedBarChart;

public class GridThemedBarChartTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return GridThemedBarChart.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "themes";
    }
}
