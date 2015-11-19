package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.other.VUMeter;

@Ignore("NO API for invalid yAxis2.getTitle().setY(-40);")
public class VUMeterTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return VUMeter.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }

}
