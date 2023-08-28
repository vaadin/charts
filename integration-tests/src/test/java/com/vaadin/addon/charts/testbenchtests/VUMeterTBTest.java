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

import com.vaadin.addon.charts.examples.other.VUMeter;
import com.vaadin.testbench.parallel.Browser;

public class VUMeterTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return VUMeter.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }

    @Override
    protected void testCustomStuff() {
        skipBrowser("VUMeter is dynamic and not good for screenshot on phantom",
                Browser.PHANTOMJS);
    }
}
