/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.container.ContainerSeriesWithSpline;

public class ContainerSeriesWithSplineTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return ContainerSeriesWithSpline.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "container";
    }

}