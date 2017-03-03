package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.dataprovider.DataProviderSeriesWithSpline;

public class DataProviderSeriesWithSplineTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return DataProviderSeriesWithSpline.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "dataprovider";
    }

}
