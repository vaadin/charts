package com.vaadin.addon.charts.testbenchtests;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.addon.charts.examples.columnandbar.ColumnWithNativeDrilldown;
import com.vaadin.testbench.By;

public class ColumnWithNativeDrilldownTBTest extends AbstractParallelTest {

    @Override
    protected String getTestViewName() {
        return ColumnWithNativeDrilldown.class.getSimpleName();
    }

    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

        waitForVaadin();
        captureAndCompare("before");

        WebElement findElement = driver.findElement(By.id("chart"));
        Action moveAndClick = new Actions(driver)
                .moveToElement(findElement, 120, 255).click()
                .moveToElement(findElement, 0, 0).build();
        moveAndClick.perform();

        waitForVaadin();
        captureAndCompare("after");
    }

    @Override
    protected String getPackageName() {
        return "columnandbar";
    }

}
