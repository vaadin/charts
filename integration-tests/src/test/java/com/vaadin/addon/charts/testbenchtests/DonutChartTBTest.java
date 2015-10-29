package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.pie.DonutChart;

@Ignore("missing innerPieOptions.getDataLabels().setDistance(-30)")
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
