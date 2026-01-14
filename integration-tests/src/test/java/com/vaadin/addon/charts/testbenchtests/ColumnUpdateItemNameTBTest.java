/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2026 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.testbenchtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.vaadin.addon.charts.examples.columnandbar.ColumnUpdateItemName;
import com.vaadin.testbench.elements.ButtonElement;

public class ColumnUpdateItemNameTBTest extends AbstractParallelTest {

    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());
        waitForVaadin();

        WebElement chart = findElement(By.id("column-update-item-name"));

        waitForColumnLabelToMatch("Unexpected initial column name", "X", chart);

        $(ButtonElement.class).id("update-button").click();
        waitForVaadin();

        waitForColumnLabelToMatch("Unexpected updated column name", "Y", chart);
    }

    private void waitForColumnLabelToMatch(String errorMessage, String expected,
            WebElement chart) {
        try {
            waitUntil(driver -> {
                return expected.equals(getColumnLabel(chart));
            });
        } catch (TimeoutException e) {
            fail(errorMessage + ", expected: " + expected + ", was: "
                    + getColumnLabel(chart));
        }
    }

    private String getColumnLabel(WebElement chart) {
        List<WebElement> xLabels = chart
                .findElements(By.className("highcharts-xaxis-labels"));
        assertEquals("Unexpect amount of columns", 1, xLabels.size());

        WebElement xLabel = xLabels.get(0);
        return xLabel.findElement(By.tagName("tspan"))
                .getAttribute("textContent");
    }

    @Override
    protected String getTestViewName() {
        return ColumnUpdateItemName.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "columnandbar";
    }
}
