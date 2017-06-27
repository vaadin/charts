package com.vaadin.addon.charts.testbenchtests;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.addon.charts.examples.columnandbar.ColumnWithNativeLazyDrilldown;

public class ColumnWithNativeLazyDrilldownTBTest extends AbstractParallelTest {

    @Override
    protected String getTestViewName() {
        return ColumnWithNativeLazyDrilldown.class.getSimpleName();
    }

    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

        waitForElementPresent(By.id("chart"), 120);
        waitForVaadin();
        captureAndCompare("before");

        WebElement element =
            driver.findElement(By.cssSelector(".highcharts-drilldown-point"));
        element.click();

        // move the cursor away from the chart
        new Actions(driver)
                .moveToElement(driver.findElement(By.className("v-ui")), 0, 0)
                .build().perform();

        waitForDynamicChanges();
        captureAndCompare("after");
    }

    @Override
    protected String getPackageName() {
        return "columnandbar";
    }

}
