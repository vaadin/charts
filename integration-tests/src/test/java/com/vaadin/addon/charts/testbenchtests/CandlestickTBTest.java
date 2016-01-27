package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.timeline.Candlestick;

public class CandlestickTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return Candlestick.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "timeline";
    }

}
