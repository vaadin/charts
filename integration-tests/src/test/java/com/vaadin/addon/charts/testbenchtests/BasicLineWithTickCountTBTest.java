package com.vaadin.addon.charts.testbenchtests;

import java.io.IOException;

import org.junit.Test;

import com.vaadin.addon.charts.examples.lineandscatter.BasicLineWithTickCount;
import com.vaadin.testbench.elements.ButtonElement;

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
    protected String getTestViewName() {
        return BasicLineWithTickCount.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
