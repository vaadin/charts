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

import com.vaadin.addon.charts.examples.lineandscatter.LineWithDashSelector;

public class DashLineTBTest extends AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        String simpleName = LineWithDashSelector.class.getSimpleName();
        return simpleName;
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
