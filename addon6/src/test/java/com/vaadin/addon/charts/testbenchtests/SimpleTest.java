package com.vaadin.addon.charts.testbenchtests;

import org.junit.Test;

public class SimpleTest extends AbstractTestBenchTest {

    @Test
    public void basic() {
        startBrowser();

        driver.get(BASEURL + "BasicTest");

        // wait for animations
        sleep(1000);

    }

}
