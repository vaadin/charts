/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.testbenchtests;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;

public abstract class AbstractSimpleScreenShotTestBenchTest extends
        AbstractParallelTest {

    @Test
    public void test() throws IOException, AssertionError {

        String pack = getPackageName();
        if (!pack.isEmpty()) {
            pack = pack + "/";
        }
        getDriver().get(BASEURL + pack + getTestViewName());
        waitUntilChartRendered();
        testCustomStuff();
        waitForVaadin();
        captureAndCompare();

    }

    /**
     * This is executed before taking the screenshot
     */
    protected void testCustomStuff() {
        getDriver().findElement(By.className("v-ui"));
    }

}
