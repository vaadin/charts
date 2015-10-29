package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.pie.PieWithGradientFill;

@Ignore("dataLabels.setConnectorColor missing API")
public class PieWithGradientFillTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return PieWithGradientFill.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "pie";
    }
}
