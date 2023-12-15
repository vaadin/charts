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

import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.addon.charts.examples.lineandscatter.SplineWithItemLabels;
import com.vaadin.testbench.parallel.Browser;

public class SplineWithItemLabelsTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return SplineWithItemLabels.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

    @Override
    public List<DesiredCapabilities> getBrowsersToTest() {
        List<DesiredCapabilities> result = super.getBrowsersToTest();
        result.remove(Browser.IE8.getDesiredCapabilities());
        return result;
    }

}
