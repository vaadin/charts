package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.other.TreeMapWithColorAxis;

@Ignore("Missing ColorAxis")
public class TreeMapWithColorAxisTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return TreeMapWithColorAxis.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
