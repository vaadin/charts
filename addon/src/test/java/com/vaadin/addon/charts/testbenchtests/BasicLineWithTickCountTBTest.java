package com.vaadin.addon.charts.testbenchtests;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.addon.charts.demoandtestapp.lineandscatter.BasicLineWithTickCount;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.parallel.Browser;

public class BasicLineWithTickCountTBTest extends AbstractParallelTest {
    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

        waitBetweenShots();
        captureAndCompare("InitialCount");

        $(ButtonElement.class).first().click();

        waitBetweenShots();
        captureAndCompare("FinalCount");
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
        return BasicLineWithTickCount.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
