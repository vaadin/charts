package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.NoDataExample;

public class NoDataExampleTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return NoDataExample.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }

}
