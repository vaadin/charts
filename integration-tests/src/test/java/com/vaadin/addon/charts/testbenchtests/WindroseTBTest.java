package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.other.WindRose;

@Ignore("Theme axes styles are not set correctly")
public class WindroseTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return WindRose.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
