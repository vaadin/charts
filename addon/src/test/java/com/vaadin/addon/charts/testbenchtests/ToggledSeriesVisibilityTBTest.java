package com.vaadin.addon.charts.testbenchtests;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.vaadin.addon.charts.demoandtestapp.columnandbar.ToggledSeriesVisibility;
import com.vaadin.testbench.By;
import com.vaadin.testbench.Parameters;

public class ToggledSeriesVisibilityTBTest extends AbstractTestBenchTest {

    boolean screenshotErrors = false;

    @Test
    public void test() throws IOException, AssertionError {

        startBrowser();
        try {

            // negligible difference when returning to 2-disable shot on chrome,
            // avoid error by increasing the tolerance
            Parameters.setScreenshotComparisonTolerance(0.03);

            driver.navigate().to(BASEURL + getTestViewName());
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

            driver.findElements(By.tagName("input")).get(1).click();
            waitBetweenShots();
            captureAndCompare("2-disable");

            driver.findElements(By.tagName("input")).get(0).click();
            waitBetweenShots();
            captureAndCompare("1-start");

        } finally {
            driver.quit();
        }
        if (screenshotErrors) {
            Assert.fail("There are differences in creenshots");
        }
    }

    protected void captureAndCompare(String name) throws IOException {
        String imageName = getTestViewName() + "-" + name + ".png";
        File refImage = getReferenceImage(imageName);

        if (!refImage.exists()) {
            System.err.println("Reference image " + refImage.getAbsolutePath()
                    + " is missing!");
        }
        if (!testBench.compareScreen(refImage)) {
            screenshotErrors = true;
        }
    }

    protected void waitBetweenShots() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected String getTestViewName() {
        return "columnandbar/" + ToggledSeriesVisibility.class.getSimpleName();
    }

}
