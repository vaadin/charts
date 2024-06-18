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

import com.vaadin.testbench.parallel.Browser;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.addon.charts.examples.pie.PieChart;

public class PieChartTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return PieChart.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "pie";
    }

    @Override
    protected void testCustomStuff() {
        super.testCustomStuff();
        skipBrowser("Can't find chart series with .cssSelector(\".highcharts-series > path\"))", Browser.IE8);
        // Ensure animation has finished before clicking
        waitForVaadin();

        WebElement firstSeriesPoint = driver.findElement(By
                .cssSelector(".highcharts-series > path"));
        Action click = new Actions(driver).moveToElement(firstSeriesPoint)
            .click().build();

        click.perform();

        waitForVaadin();

        boolean containsFirefox = driver
                .findElement(By.className("v-Notification")).getText()
                .contains("Firefox");
        Assert.assertTrue(containsFirefox);

    }
}
