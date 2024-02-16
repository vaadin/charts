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

import com.vaadin.addon.charts.examples.lineandscatter.TimeDataWithIrregularIntervalsAndLocalizedTexts;
import com.vaadin.testbench.By;

public class TimeDataWithLocalizationsTBTest extends AbstractParallelTest {

    boolean screenshotErrors = false;

    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

        // click default first to give focus to button
        driver.findElement(By.id("en-button")).click();

        waitForVaadin();
        captureAndCompare("1-start");

        driver.findElement(By.id("fi-button")).click();
        waitForVaadin();
        captureAndCompare("2-removed");

        driver.findElement(By.id("en-button")).click();
        waitForVaadin();
        captureAndCompare("1-start");

    }

    @Override
    protected String getTestViewName() {
        return TimeDataWithIrregularIntervalsAndLocalizedTexts.class
                .getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
