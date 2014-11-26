package com.vaadin.addon.charts.testbenchtests;

import org.junit.Test;

import com.vaadin.addon.charts.demoandtestapp.BasicTest;

public class SimpleTBTest extends AbstractParallelTest {

    @Test
    public void basic() {
        driver.get(getTestUrl());

        // wait for animations
        sleep(1000);

    }

    @Override
    protected String getTestViewName() {
        return BasicTest.class.getSimpleName();
    }

}
