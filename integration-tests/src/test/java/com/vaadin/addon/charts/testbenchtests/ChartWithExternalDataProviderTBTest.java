/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
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
