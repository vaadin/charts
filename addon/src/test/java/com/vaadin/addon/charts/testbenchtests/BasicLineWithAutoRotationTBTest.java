package com.vaadin.addon.charts.testbenchtests;

import java.io.IOException;

import org.junit.Test;

import com.vaadin.addon.charts.demoandtestapp.lineandscatter.BasicLineWithAutoRotation;
import com.vaadin.testbench.elements.ButtonElement;

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

    @Override
    protected String getTestViewName() {
        return BasicLineWithAutoRotation.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
