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

import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.vaadin.addon.charts.examples.columnandbar.ColumnWithNativeLazyDrilldownByIndex;
import com.vaadin.testbench.By;

public class ColumnWithNativeLazyDrilldownByIndexTBTest extends
        AbstractParallelTest {

    @Override
    protected String getTestViewName() {
        return ColumnWithNativeLazyDrilldownByIndex.class.getSimpleName();
    }

    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

        waitForVaadin();
        captureAndCompare("before");

        WebElement element =
            driver.findElement(By.cssSelector(".highcharts-drilldown-point"));
        element.click();

        waitForDynamicChanges();
        captureAndCompare("after");
    }

    @Override
    protected String getPackageName() {
        return "columnandbar";
    }

}
