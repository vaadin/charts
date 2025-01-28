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

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.addon.charts.examples.lineandscatter.BasicLineGettingMousePointerPosition;
import com.vaadin.testbench.By;
import com.vaadin.testbench.parallel.Browser;

public class BasicLineGettingMousePointerPositionTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return BasicLineGettingMousePointerPosition.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

    @Override
    protected void testCustomStuff() {
        skipBrowser("Move and click action does not seem to work with Firefox", Browser.FIREFOX);
        WebElement findElement = driver.findElement(By.id("chart"));
        Action click;
        // Point click, needs to hover on point before click
        // Note, coordinates are for the point, not for the click, so exactly
        // 80,315 should not be expected in UI
        click = new Actions(driver).moveToElement(findElement, 84, 315).build();
        click.perform();
        waitForVaadin();
        click = new Actions(driver).moveToElement(findElement, 85, 315).build();
        click.perform();
        waitForVaadin();
        click = new Actions(driver).click().build();
        click.perform();

        // Chart click
        click = new Actions(driver).moveToElement(findElement, 100, 100)
                .click().build();

        click.perform();
        waitForVaadin();
    }

}
