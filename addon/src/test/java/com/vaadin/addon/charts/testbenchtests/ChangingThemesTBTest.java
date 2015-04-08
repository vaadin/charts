package com.vaadin.addon.charts.testbenchtests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.addon.charts.demoandtestapp.themes.ChangingThemes;
import com.vaadin.testbench.By;
import com.vaadin.testbench.parallel.Browser;

public class ChangingThemesTBTest extends AbstractParallelTest {

    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

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
        captureAndCompare("6-valoLight");

        driver.findElement(By.id("vd-button")).click();
        waitBetweenShots();
        captureAndCompare("7-valoDark");

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
        return ChangingThemes.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "themes";
    }

    @Override
    public List<DesiredCapabilities> getBrowsersToTest() {
        List<DesiredCapabilities> result = super.getBrowsersToTest();
        // FIXME: dynamic changes causing red spinner in IE8
        result.remove(Browser.IE8.getDesiredCapabilities());
        return result;
    }

}
