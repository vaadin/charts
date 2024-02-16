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

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.addon.charts.examples.columnandbar.ColumnWithShapedTooltip;
import com.vaadin.testbench.By;
import com.vaadin.testbench.parallel.Browser;

public class ColumnWithShapedTooltipTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return ColumnWithShapedTooltip.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "columnandbar";
    }

    @Override
    protected void testCustomStuff() {
        super.testCustomStuff();
        WebElement findElement = driver.findElement(By.id("chart"));
        Action move = new Actions(driver).moveToElement(findElement, 415, 280)
                .build();
        move.perform();
    }

    @Override
    public List<DesiredCapabilities> getBrowsersToTest() {
        List<DesiredCapabilities> result = super.getBrowsersToTest();
        result.remove(Browser.PHANTOMJS.getDesiredCapabilities());
        return result;
    }
}
