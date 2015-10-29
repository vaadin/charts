package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.area.StackedArea;

@Ignore("missing labels.setFormatter for functions")
public class StackedAreaTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return StackedArea.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "area";
    }
}
