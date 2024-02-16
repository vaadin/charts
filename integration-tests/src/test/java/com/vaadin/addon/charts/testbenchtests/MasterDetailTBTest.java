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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.addon.charts.examples.dynamic.MasterDetailChart;
import com.vaadin.testbench.By;

public class MasterDetailTBTest extends AbstractParallelTest {

    @Override
    protected String getTestViewName() {
        return MasterDetailChart.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "dynamic";
    }

    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

        waitForVaadin();
        captureAndCompare("before");

        WebElement findElement = driver.findElement(By.id("master-chart"));
        Action click = new Actions(driver).moveToElement(findElement, 150, 40)
                .clickAndHold().moveByOffset(100, 0).release().build();
        click.perform();

        waitForVaadin();
        captureAndCompare("after");
    }
}
