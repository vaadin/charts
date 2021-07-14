package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.themes.SkiesThemedPieChart;

import org.junit.Ignore;

@Ignore("test view uses an image that is pointing to an URL that no longer works")
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
