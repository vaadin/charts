package com.vaadin.addon.charts.testbenchtests;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        sleep(getScreenShotDelay());

        captureAndCompare();

    }

    /**
     * Waits until the chart element has been rendered on screen. This is
     * necessary in the cases where the test grid is overloaded.
     */
    protected void waitUntilChartRendered() {
        new WebDriverWait(driver, 90).until(ExpectedConditions
                .presenceOfElementLocated(com.vaadin.testbench.By
                        .className("highcharts-container")));
    }

    /**
     * This is executed before taking the screenshot
     */
    protected void testCustomStuff() {
        getDriver().findElement(By.className("v-ui"));
    }

    protected int getScreenShotDelay() {
        return 5000;
    }

}
