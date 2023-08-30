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

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.addon.charts.examples.dynamic.ClickToAddPoint;
import com.vaadin.testbench.By;
import com.vaadin.testbench.parallel.Browser;

public class ClickToAddPointTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return ClickToAddPoint.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "dynamic";
    }

    @Override
    protected void testCustomStuff() {
        skipBrowser("Move and click action does not seem to work with Firefox", Browser.FIREFOX);
        WebElement findElement = driver.findElement(By.id("chart"));
        Action click = new Actions(driver).moveToElement(findElement, 200, 200)
                .click().build();

        click.perform();

        waitForVaadin(); // FIXME investigate why randomly fails without this

        assertTrue(eventLogText().startsWith("Added"));

        click.perform();

        waitForVaadin(); // FIXME investigate why randomly fails without this

        assertTrue(eventLogText().startsWith("Removed"));
    }

    private String eventLogText() {
        return driver.findElement(By.id("lastAction")).getText();
    }

}
