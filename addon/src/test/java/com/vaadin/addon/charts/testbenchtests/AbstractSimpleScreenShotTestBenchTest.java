package com.vaadin.addon.charts.testbenchtests;

import static org.junit.Assert.assertTrue;

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
        testCustomStuff();
        sleep(getScreenShotDelay());

        assertTrue(testBench(driver).compareScreen(getTestViewName()));

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
