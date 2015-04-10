package com.vaadin.addon.charts.testbenchtests;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.addon.charts.demoandtestapp.lineandscatter.TimeSeriesIntervalUnit;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.parallel.Browser;

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
    public List<DesiredCapabilities> getBrowsersToTest() {
        List<DesiredCapabilities> result = super.getBrowsersToTest();
        // FIXME: dynamic changes causing red spinner in IE8
        result.remove(Browser.IE8.getDesiredCapabilities());
        return result;
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
