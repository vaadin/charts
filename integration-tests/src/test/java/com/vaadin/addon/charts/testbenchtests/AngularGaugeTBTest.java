package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.other.AngularGauge;

@Ignore("Theme axes styles are not set correctly")
public class AngularGaugeTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return AngularGauge.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
