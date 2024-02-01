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

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.addon.charts.examples.pie.PieWithLegendAndMouseDetails;
import com.vaadin.testbench.parallel.BrowserUtil;

public class PieLegendItemClickMouseDetailsTBTest extends AbstractParallelTest {

    @Test
    public void testLegendPointIndexMouseDetails() {
        driver.get(getTestUrl());

        WebElement item = driver.findElement(By.className("highcharts-legend-item"));
        item.click();

        WebElement mouseDetails = getMouseDetails();
        String text = mouseDetails.getText();

        // We cannot assert the abs and rel values accurately between browsers
        // and resolutions. Do rough checks...
        Assert.assertTrue("Absolute X value wasn't in the expected 200px range",
                text.contains("AbsX: 2"));
        Assert.assertTrue("Absolute Y value wasn't in the expected 400px range",
                text.contains("AbsY: 4"));

        Assert.assertTrue("Relative X value wasn't in the expected 100px range",
                text.contains("RelX: 1"));
        Assert.assertTrue("Relative Y value wasn't in the expected 300px range",
                text.contains("RelY: 3"));

        Assert.assertTrue(
                "The mouse details element did not contain the meta information from the last click: was:"
                        + text,
                text.contains(
                        "Bttn: left Alt: false Ctrl: false Meta: false Shift: false"));

        // These cannot be run against IE automatically...
        if (!BrowserUtil.isIE(getDesiredCapabilities())) {
            validateMouseDetailsText(item, true, true, true);
            validateMouseDetailsText(item, true, true, false);
            validateMouseDetailsText(item, true, false, true);
            validateMouseDetailsText(item, true, false, false);
            validateMouseDetailsText(item, false, true, true);
            validateMouseDetailsText(item, false, true, false);
            validateMouseDetailsText(item, false, false, true);
            validateMouseDetailsText(item, false, false, false);
        }

    }

    private void validateMouseDetailsText(WebElement legendItemToClick,
            boolean shiftPressed, boolean commandPressed, boolean altPressed) {

        if (BrowserUtil.isPhantomJS(getDesiredCapabilities())) {
            // Phantom on linux doesn't seem to support meta key...
            commandPressed = false;
        }

        Actions actions = new Actions(driver);
        if (shiftPressed) {
            actions = actions.keyDown(Keys.SHIFT);
        }

        if (commandPressed) {
            if (getDesiredCapabilities().getPlatform().equals(Platform.LINUX)) {
                actions = actions.keyDown(Keys.META);
            } else {
                actions = actions.keyDown(Keys.COMMAND);
            }
        }

        if (altPressed) {
            actions = actions.keyDown(Keys.ALT);
        }

        actions = actions.click(legendItemToClick);

        if (shiftPressed) {
            actions = actions.keyUp(Keys.SHIFT);
        }

        if (commandPressed) {
            if (getDesiredCapabilities().getPlatform().is(Platform.LINUX)) {
                actions = actions.keyUp(Keys.META);
            } else {
                actions = actions.keyUp(Keys.COMMAND);
            }

        }

        if (altPressed) {
            actions = actions.keyUp(Keys.ALT);
        }

        actions.perform();

        String text = getMouseDetails().getText();
        Assert.assertTrue(
                "Mouse details did not include button clicked, cannot assert meta information: Details was: "
                        + text,
                text.contains("Bttn: left "));

        // We cannot reliably test the x/y positions across browsers so ignore
        // them...
        String actual = text.split("Bttn: left ")[1];
        StringBuilder sb = new StringBuilder();
        sb.append("Alt: ").append(altPressed);
        // Can't test control as it opens ctx menu..
        sb.append(" Ctrl: false");
        sb.append(" Meta: ").append(commandPressed);
        sb.append(" Shift: ").append(shiftPressed);
        String expected = sb.toString();

        Assert.assertEquals(
                "Mouse details did not contain expected meta data! ", expected,
                actual);

    }

    private WebElement getMouseDetails() {
        return driver
                .findElement(By.id(PieWithLegendAndMouseDetails.MOUSE_DETAILS));
    }

    @Override
    protected String getTestViewName() {
        return PieWithLegendAndMouseDetails.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "pie";
    }
}
