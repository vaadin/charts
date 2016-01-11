package com.vaadin.addon.charts.testbenchtests;

import java.io.IOException;

import org.junit.Test;

import com.vaadin.addon.charts.examples.lineandscatter.TimeSeriesIntervalUnit;
import com.vaadin.testbench.elements.ButtonElement;

public class TimeSeriesIntervalUnitTBTest extends AbstractParallelTest {
    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

        waitForVaadin();
        captureAndCompare("OneMonthInterval");

        $(ButtonElement.class).first().click();

        waitForVaadin();
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
