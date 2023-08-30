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

import com.vaadin.addon.charts.examples.timeline.RangeSelectorCustomDateParser;
import com.vaadin.testbench.parallel.Browser;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class RangeSelectorCustomDateParserTBTest extends AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        return RangeSelectorCustomDateParser.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "timeline";
    }

    @Override
    protected void testCustomStuff() {
        skipBrowser(
                "Changing range via Selenium doesn't trigger chart redraw for FF",
                Browser.FIREFOX, Browser.IE8);
        super.testCustomStuff();
        waitForVaadin();

        String startRange = "00:02:00.000";
        String endRange = "00:05:00.000";

        setRangeValue(startRange, findRangeStartInput());
        setRangeValue(endRange, findRangeEndInput());
    }

    private void setRangeValue(String value, WebElement input) {
        Actions action = new Actions(driver);
        //Should be done as one action, otherwise does not work
        action.doubleClick(input)
                .sendKeys(Keys.chord(Keys.CONTROL, "a"))
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(value)
                .sendKeys(Keys.TAB)
                .perform();
    }

    private WebElement findRangeStartInput() {
        List<WebElement> list = driver.findElements(
                com.vaadin.testbench.By.cssSelector("#highcharts-0 > svg > g.highcharts-input-group > g:nth-child(2) > text"));
        return list.get(0);
    }

    private WebElement findRangeEndInput() {
        List<WebElement> list = driver.findElements(
                com.vaadin.testbench.By.cssSelector("#highcharts-0 > svg > g.highcharts-input-group > g:nth-child(4) > text"));
        return list.get(0);
    }
}