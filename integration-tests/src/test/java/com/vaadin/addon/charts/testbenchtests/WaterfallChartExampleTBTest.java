package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.other.WaterfallChartExample;

@Ignore("missing labels.setFormatter for functions")
public class WaterfallChartExampleTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return WaterfallChartExample.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }

}
