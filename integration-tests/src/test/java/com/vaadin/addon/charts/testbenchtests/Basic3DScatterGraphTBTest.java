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

import com.vaadin.addon.charts.examples.threed.Basic3DScatter;

public class Basic3DScatterGraphTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return Basic3DScatter.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "threed";
    }
}
