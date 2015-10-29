package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.other.PolarChart;

@Ignore("Theme axes styles are not set correctly")
public class PolarChartTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return PolarChart.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
