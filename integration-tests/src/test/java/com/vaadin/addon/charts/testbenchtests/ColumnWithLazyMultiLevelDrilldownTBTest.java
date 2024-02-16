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

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.addon.charts.examples.columnandbar.ColumnWithLazyMultiLevelDrilldown;
import com.vaadin.testbench.By;
import com.vaadin.testbench.elements.LabelElement;

public class ColumnWithLazyMultiLevelDrilldownTBTest extends
        AbstractParallelTest {

    @Override
    protected String getTestViewName() {
        return ColumnWithLazyMultiLevelDrilldown.class.getSimpleName();
    }

    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

        waitForVaadin();

        WebElement findElement = driver.findElement(By.id("chart"));
        clickFirstDrilldownPoint();
        assertLogText("DrilldownEvent: Latin America and Carribean");

        clickFirstDrilldownPoint();
        assertLogText("DrilldownEvent: Costa Rica");

        clickDrilldownUp();
        assertLogText("ChartDrillupEvent");
    }

    private void clickFirstDrilldownPoint() {
        WebElement element =
            driver.findElement(By.cssSelector(".highcharts-drilldown-point"));
        element.click();
    }

    private void clickDrilldownUp() {
        WebElement element =
            driver.findElement(By.cssSelector(".highcharts-button"));
        element.click();
    }

    private void assertLogText(String text) {
        waitForVaadin();
        assertTrue(
                String.format("Couldn't find text '%s' from the log.", text),
                logContainsText(text));
    }

    private boolean logContainsText(String string) {
        LabelElement label = $(LabelElement.class).first();
        if (label.getText().contains(string)) {
            return true;
        }

        return false;
    }

    @Override
    protected String getPackageName() {
        return "columnandbar";
    }

}
