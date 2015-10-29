package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.other.GaugeWithDualAxes;

@Ignore("Theme axes styles are not set correctly")
public class GaugeWithDualAxesTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return GaugeWithDualAxes.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }

}
