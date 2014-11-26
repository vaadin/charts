package com.vaadin.addon.charts.testbenchtests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.vaadin.addon.charts.demoandtestapp.lineandscatter.TimeDataWithIrregularIntervalsAndLocalizedTexts;
import com.vaadin.testbench.By;

public class TimeDataWithLocalizationsTBTest extends AbstractParallelTest {

    boolean screenshotErrors = false;

    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

        // click default first to give focus to button
        driver.findElement(By.id("en-button")).click();

        waitBetweenShots();
        captureAndCompare("1-start");

        driver.findElement(By.id("fi-button")).click();
        waitBetweenShots();
        captureAndCompare("2-removed");

        driver.findElement(By.id("en-button")).click();
        waitBetweenShots();
        captureAndCompare("1-start");

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
        return TimeDataWithIrregularIntervalsAndLocalizedTexts.class
                .getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }
}
