package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.other.Spiderweb;

@Ignore("Theme axes styles are not set correctly")
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
