package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.other.BubbleChartMaxSizePercentage;

public class BubbleChartMaxSizePercentageTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return BubbleChartMaxSizePercentage.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }

}
