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

import java.io.IOException;

import org.junit.Test;

import com.vaadin.addon.charts.examples.lineandscatter.SplineWithPlotBandRemoveFunctionality;
import com.vaadin.testbench.elements.ButtonElement;

public class SplineWithPlotBandRemoveFunctionalityTBTest extends
        AbstractParallelTest {

    boolean screenshotErrors = false;

    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());
        ButtonElement button = $(ButtonElement.class).first();
        // toggle first to give focus for button
        button.click();
        button.click();

        waitForVaadin();
        captureAndCompare("1-start");

        button.click();
        waitForVaadin();
        captureAndCompare("2-removed");

        button.click();
        waitForVaadin();
        captureAndCompare("1-start");
    }

    @Override
    protected String getTestViewName() {
        return SplineWithPlotBandRemoveFunctionality.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
