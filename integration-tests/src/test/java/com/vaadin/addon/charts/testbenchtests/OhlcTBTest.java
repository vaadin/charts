package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.timeline.Ohlc;

public class OhlcTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return Ohlc.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "timeline";
    }

}
