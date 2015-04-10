package com.vaadin.addon.charts.testbenchtests;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.addon.charts.demoandtestapp.lineandscatter.TimeDataWithIrregularIntervalsAndLocalizedTexts;
import com.vaadin.testbench.By;
import com.vaadin.testbench.parallel.Browser;

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

    @Override
    protected String getTestViewName() {
        return TimeDataWithIrregularIntervalsAndLocalizedTexts.class
                .getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

    @Override
    public List<DesiredCapabilities> getBrowsersToTest() {
        List<DesiredCapabilities> result = super.getBrowsersToTest();
        // FIXME: dynamic changes causing red spinner in IE8
        result.remove(Browser.IE8.getDesiredCapabilities());
        return result;
    }
}
