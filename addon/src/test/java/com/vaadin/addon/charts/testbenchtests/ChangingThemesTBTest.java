package com.vaadin.addon.charts.testbenchtests;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.vaadin.addon.charts.demoandtestapp.themes.ChangingThemes;
import com.vaadin.testbench.By;

public class ChangingThemesTBTest extends AbstractTestBenchTest {

    boolean screenshotErrors = false;

    @Test
    public void test() throws IOException, AssertionError {

        startBrowser();
        try {
            driver.navigate().to(BASEURL + getTestViewName());

            waitBetweenShots();
            waitBetweenShots();

            captureAndCompare("1-start");

            driver.findElement(By.id("vaadin-button")).click();
            waitBetweenShots();
            captureAndCompare("2-vaadin");

            driver.findElement(By.id("grid-button")).click();
            waitBetweenShots();
            captureAndCompare("3-grid");

            driver.findElement(By.id("skies-button")).click();
            waitBetweenShots();
            captureAndCompare("4-skies");

            driver.findElement(By.id("gray-button")).click();
            waitBetweenShots();
            captureAndCompare("5-gray");

            driver.findElement(By.id("vl-button")).click();
            waitBetweenShots();
            captureAndCompare("6-vaadinLight");

            driver.findElement(By.id("vd-button")).click();
            waitBetweenShots();
            captureAndCompare("7-vaadinDark");

            // Now check again that pictures match

            driver.findElement(By.id("vaadin-button")).click();
            waitBetweenShots();
            captureAndCompare("2-vaadin");

            driver.findElement(By.id("skies-button")).click();
            waitBetweenShots();
            captureAndCompare("4-skies");

            driver.findElement(By.id("grid-button")).click();
            waitBetweenShots();
            captureAndCompare("3-grid");

        } finally {
            driver.quit();
        }
        if (screenshotErrors) {
            Assert.fail("There are differences in screenshots");
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
        return "themes/" + ChangingThemes.class.getSimpleName();
    }
}
