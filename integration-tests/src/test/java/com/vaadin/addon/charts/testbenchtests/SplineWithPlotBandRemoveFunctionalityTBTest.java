package com.vaadin.addon.charts.testbenchtests;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.vaadin.addon.charts.examples.lineandscatter.SplineWithPlotBandRemoveFunctionality;
import com.vaadin.testbench.By;

public class SplineWithPlotBandRemoveFunctionalityTBTest extends
        AbstractParallelTest {

    boolean screenshotErrors = false;

    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());
        WebElement button = driver.findElement(By.id("vaadin-button"));
        // toggle first to give focus for button
        button.click();
        button.click();

        waitForVaadin();
        captureAndCompare("1-start");

        button.click();
        waitForVaadin();
        captureAndCompare("2-removed");

        button.click();

        waitForVaadin();
        captureAndCompare("1-start");

    }

    @Override
    protected String getTestViewName() {
        return SplineWithPlotBandRemoveFunctionality.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
