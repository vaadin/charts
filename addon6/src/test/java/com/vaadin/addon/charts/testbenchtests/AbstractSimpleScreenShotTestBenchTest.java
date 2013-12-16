package com.vaadin.addon.charts.testbenchtests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;

public abstract class AbstractSimpleScreenShotTestBenchTest extends
        AbstractTestBenchTest {

    @Test
    public void test() throws IOException, AssertionError {

        startBrowser();
        try {
            String pack = getPackageName();
            if (!pack.isEmpty()) {
                pack = pack + "/";
            }
            driver.navigate().to(BASEURL + pack + getTestViewName());
            testCustomStuff();
            sleep(getScreenShotDelay());

            final String imageName = getTestViewName() + ".png";
            final File refImage = getReferenceImage(imageName);

            if (!refImage.exists()) {
                System.err.println("Reference image "
                        + refImage.getAbsolutePath() + " is missing!");
            }
            try {
                assertTrue(testBench.compareScreen(refImage));
            } catch (AssertionError e) {
                // re try a moment later if animations were still in progress
                sleep(getScreenShotDelay());
                assertTrue(testBench.compareScreen(refImage));
            }
        } finally {
            driver.quit();
        }
    }

    /**
     * This is executed before taking the screenshot
     */
    protected void testCustomStuff() {
        // This ensures UI is rendered before timeout for animations is started
        driver.findElement(By.className("v-ui"));
    }

    protected String getPackageName() {
        return "";
    }

    protected int getScreenShotDelay() {
        return 5000;
    }

    protected abstract String getTestViewName();
}
