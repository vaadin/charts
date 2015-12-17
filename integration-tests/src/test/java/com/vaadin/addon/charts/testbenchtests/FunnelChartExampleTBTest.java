package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.FunnelChartExample;
import org.junit.Ignore;

@Ignore("Theme axes styles are not set correctly")
public class FunnelChartExampleTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return FunnelChartExample.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }

}
