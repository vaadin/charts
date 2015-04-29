package com.vaadin.addon.charts.testbenchtests;

import java.io.IOException;

import org.junit.Test;

import com.vaadin.addon.charts.demoandtestapp.lineandscatter.TimeSeriesIntervalUnit;
import com.vaadin.testbench.elements.ButtonElement;

public class TimeSeriesIntervalUnitTBTest extends AbstractParallelTest {
    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

        waitBetweenShots();
        captureAndCompare("OneMonthInterval");

        $(ButtonElement.class).first().click();

        waitBetweenShots();
        captureAndCompare("OneDayInterval");
    }

    @Override
    protected String getTestViewName() {
        return TimeSeriesIntervalUnit.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
