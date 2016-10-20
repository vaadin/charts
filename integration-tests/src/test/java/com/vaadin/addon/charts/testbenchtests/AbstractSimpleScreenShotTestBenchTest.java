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
        captureAndCompare();

    }

    /**
     * This is executed before taking the screenshot
     */
    protected void testCustomStuff() {
        getDriver().findElement(By.className("v-ui"));
    }

}
