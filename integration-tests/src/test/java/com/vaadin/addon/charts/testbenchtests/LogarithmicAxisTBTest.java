/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.lineandscatter.LogarithmicAxis;

public class LogarithmicAxisTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        String simpleName = LogarithmicAxis.class.getSimpleName();
        return simpleName;
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }
}
