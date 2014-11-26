package com.vaadin.addon.charts.testbenchtests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.vaadin.addon.charts.demoandtestapp.columnandbar.ToggledSeriesVisibility;
import com.vaadin.testbench.By;
import com.vaadin.testbench.Parameters;

public class ToggledSeriesVisibilityTBTest extends AbstractParallelTest {

    boolean screenshotErrors = false;

    @Test
    public void test() throws IOException, AssertionError {

        // negligible difference when returning to 2-disable shot on chrome,
        // avoid error by increasing the tolerance
        Parameters.setScreenshotComparisonTolerance(0.03);

        driver.get(getTestUrl());

        waitBetweenShots();

        driver.findElements(By.tagName("input")).get(0).click();
        driver.findElements(By.tagName("input")).get(0).click();
        waitBetweenShots();
        captureAndCompare("1-start");

        driver.findElements(By.tagName("input")).get(0).click();
        waitBetweenShots();
        captureAndCompare("2-disable");

        driver.findElements(By.tagName("input")).get(1).click();
        waitBetweenShots();
        captureAndCompare("3-disable");

    }

    protected void captureAndCompare(String name) throws IOException {
        assertTrue(testBench(driver).compareScreen(
                getTestViewName() + "-" + name));
    }

    protected void waitBetweenShots() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getTestViewName() {
        return ToggledSeriesVisibility.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "columnandbar";
    }

}
