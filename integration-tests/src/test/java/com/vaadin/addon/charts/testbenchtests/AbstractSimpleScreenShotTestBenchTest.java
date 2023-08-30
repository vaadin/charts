/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.testbench.parallel.BrowserUtil;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

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
        getTestBenchCommandExecutor().waitForVaadin();
        addDelayForIE8();
        captureAndCompare();

    }

    private void addDelayForIE8() {
        DesiredCapabilities capabilities = getDesiredCapabilities();

        if(BrowserUtil.isIE8(capabilities)) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * This is executed before taking the screenshot
     */
    protected void testCustomStuff() {
        getDriver().findElement(By.className("v-ui"));
    }

}
