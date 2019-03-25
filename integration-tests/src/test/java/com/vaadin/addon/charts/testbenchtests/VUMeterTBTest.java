package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.VUMeter;
import com.vaadin.testbench.parallel.Browser;

public class VUMeterTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return VUMeter.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }

    @Override
    protected void testCustomStuff() {
        skipBrowser("VUMeter is dynamic and not good for screenshot on phantom",
                Browser.PHANTOMJS);
    }
}
