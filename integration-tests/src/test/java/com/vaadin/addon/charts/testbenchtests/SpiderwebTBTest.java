package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.Spiderweb;


public class SpiderwebTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return Spiderweb.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
