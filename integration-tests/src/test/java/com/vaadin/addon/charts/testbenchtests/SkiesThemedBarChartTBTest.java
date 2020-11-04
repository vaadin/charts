package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.themes.SkiesThemedBarChart;

// Ignoring for now because the test view uses an image that is pointing to an URL that no longer works.
@Ignore
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
