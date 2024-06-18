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

import com.vaadin.addon.charts.examples.themes.ChangingThemes;
import com.vaadin.testbench.By;

public class ChangingThemesTBTest extends AbstractParallelTest {

    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

        waitForVaadin();

        captureAndCompare("1-start");

        driver.findElement(By.id("vaadin-button")).click();
        waitForVaadin();
        captureAndCompare("2-vaadin");

        driver.findElement(By.id("grid-button")).click();
        waitForVaadin();
        captureAndCompare("3-grid");

        driver.findElement(By.id("skies-button")).click();
        waitForVaadin();
        captureAndCompare("4-skies");

        driver.findElement(By.id("gray-button")).click();
        waitForVaadin();
        captureAndCompare("5-gray");

        driver.findElement(By.id("vl-button")).click();
        waitForVaadin();
        captureAndCompare("6-valoLight");

        driver.findElement(By.id("vd-button")).click();
        waitForVaadin();
        captureAndCompare("7-valoDark");

    }

    @Override
    protected String getTestViewName() {
        return ChangingThemes.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "themes";
    }

}
