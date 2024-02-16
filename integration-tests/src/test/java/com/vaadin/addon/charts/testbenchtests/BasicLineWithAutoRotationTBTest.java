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

import java.io.IOException;

import org.junit.Test;

import com.vaadin.addon.charts.examples.lineandscatter.BasicLineWithAutoRotation;
import com.vaadin.testbench.elements.ButtonElement;

public class BasicLineWithAutoRotationTBTest extends AbstractParallelTest {
    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

        waitForVaadin();
        captureAndCompare("InitialWidth");

        $(ButtonElement.class).first().click();

        waitForVaadin();
        captureAndCompare("FinalWidth");
    }

    @Override
    protected String getTestViewName() {
        return BasicLineWithAutoRotation.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
