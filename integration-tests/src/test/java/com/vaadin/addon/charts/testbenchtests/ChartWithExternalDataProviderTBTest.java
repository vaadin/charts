package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.dataprovider.ChartWithExternalDataProvider;

public class ChartWithExternalDataProviderTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return ChartWithExternalDataProvider.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "dataprovider";
    }
}
