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

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vaadin.addon.charts.examples.columnandbar.BarWithDisabledState;
import com.vaadin.client.StyleConstants;
import com.vaadin.testbench.elements.ButtonElement;

public class BarWithDisabledStateTBTest extends AbstractParallelTest {

    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

        waitForVaadin();

        WebElement chart = findElement(By.id("bar-with-disabled-state"));

        ButtonElement disableButton = $(ButtonElement.class).id("disable-button");
        disableButton.click();
        waitForVaadin();

        Assert.assertTrue(chart.getAttribute("class").contains(StyleConstants.DISABLED));

        disableButton.click();
        waitForVaadin();

        Assert.assertFalse(chart.getAttribute("class").contains(StyleConstants.DISABLED));

    }

    @Override
    protected String getTestViewName() {
        return BarWithDisabledState.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "columnandbar";
    }

}
