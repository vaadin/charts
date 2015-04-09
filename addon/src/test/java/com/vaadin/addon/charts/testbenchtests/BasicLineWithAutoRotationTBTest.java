package com.vaadin.addon.charts.testbenchtests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.addon.charts.demoandtestapp.lineandscatter.BasicLineWithAutoRotation;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.parallel.Browser;

public class BasicLineWithAutoRotationTBTest extends AbstractParallelTest {
    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

        waitBetweenShots();
        captureAndCompare("InitialWidth");

        $(ButtonElement.class).first().click();

        waitBetweenShots();
        captureAndCompare("FinalWidth");
    }

    protected void captureAndCompare(String name) throws IOException {
        assertTrue(testBench(driver).compareScreen(
                getTestViewName() + "-" + name));

    }

    protected void waitBetweenShots() {
        sleep(5000);
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
        return BasicLineWithAutoRotation.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
