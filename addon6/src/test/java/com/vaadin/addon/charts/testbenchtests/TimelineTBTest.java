package com.vaadin.addon.charts.testbenchtests;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.vaadin.testbench.By;

public class TimelineTBTest extends AbstractTestBenchTest {

    private StringBuffer screenComparisonErrors;

    /**
     * Clicks an element specified by the provided vaadin locator at the given
     * coordinates relative to the upper left corner of the element.
     * 
     * @param locator
     *            A vaadin locator
     * @param x
     * @param y
     */
    private void click(String locator, int x, int y) {
        testBenchElement(driver.findElement(By.vaadin(locator))).click(x, y);
    }

    private void compareScreen(String imageQualifier) throws IOException {
        File refImage = getReferenceImage(String.format("timeline-%s.png",
                imageQualifier));
        try {
            try {
                // force focus away
                click("ROOT::PID_Smenu#item0", 16, 13);
                click("ROOT::PID_Smenu#item0", 16, 13);
            } catch (Exception e) {

            }
            sleep(2000);
            if (!testBench.compareScreen(refImage)) {
                screenComparisonErrors.append("Screen comparison failed for ")
                        .append(refImage.getAbsolutePath()).append("\n");
            }
        } catch (AssertionError e) {
            screenComparisonErrors.append(e.getMessage()).append("\n");
        }
    }

    @Before
    public void setUp() {
        super.setUp();
        startBrowser();
        screenComparisonErrors = new StringBuffer();
    }

    @After
    public void tearDown() {
        super.tearDown();
        if (screenComparisonErrors.length() > 0) {
            System.err.println(screenComparisonErrors);
            fail();
        }
    }

    @Test
    public void testGraphStrokeSize() throws Exception {
        driver.get(concatUrl(BASEURL,
                "/timeline/TimelineFeatures?restartApplication"));
        click("ROOT::PID_Smenu#item1", 21, 8);
        click("ROOT::Root/VOverlay[0]/VMenuBar[0]#item0", 21, 10);
        click("ROOT::Root/VOverlay[1]/VMenuBar[0]#item6", 10, 12);
        compareScreen("graph4pxwide");
    }

    @Test
    public void testTimelineGraphLinecaps() throws Exception {
        driver.get(concatUrl(BASEURL,
                "/timeline/TimelineFeatures?restartApplication"));

        // Load CSV data
        click("ROOT::PID_Smenu#item0", 16, 13);
        click("ROOT::Root/VOverlay[0]/VMenuBar[0]#item4", 41, 5);

        // Turn linecaps on
        click("ROOT::PID_Smenu#item1", 19, 10);
        click("ROOT::Root/VOverlay[0]/VMenuBar[0]#item4", 27, 9);
        compareScreen("with-linecaps");

        // Turn linecaps off
        click("ROOT::PID_Smenu#item1", 19, 10);
        click("ROOT::Root/VOverlay[0]/VMenuBar[0]#item4", 27, 9);
        compareScreen("without-linecaps");

        // Turn stacking on and test linecaps
        click("ROOT::PID_Smenu#item1", 28, 8);
        click("ROOT::Root/VOverlay[0]/VMenuBar[0]#item3", 41, 7);
        click("ROOT::PID_Smenu#item1", 20, 11);
        click("ROOT::Root/VOverlay[0]/VMenuBar[0]#item4", 34, 5);
        compareScreen("stacked-with-linecaps");
    }

    @Test
    public void testTimelineMoonwalking() throws Exception {
        driver.get(concatUrl(BASEURL,
                "/timeline/GetSetDateRangeTest?restartApplication"));
        driver.findElement(By.xpath("//div[@class=\"zoom-level\"]")).click();

        WebElement reset = driver.findElement(By
                .vaadin("ROOT::PID_Sreset-button/domChild[0]/domChild[0]"));
        reset.click();

        // Now clicking several times should not move the graph
        reset.click();
        reset.click();
        reset.click();
        reset.click();
        compareScreen("4clicks");

        reset.click();
        reset.click();
        reset.click();
        reset.click();
        compareScreen("8clicks");
    }

    @Test
    public void testTimelinePreEpoch() throws Exception {
        driver.get(concatUrl(BASEURL,
                "/timeline/TimelineFeatures?restartApplication"));
        click("ROOT::PID_Smenu#item0", 41, 6);
        click("ROOT::Root/VOverlay[0]/VMenuBar[0]#item8", 55, 7);
        compareScreen("pre-epoch");
    }

    @Test
    public void testTimelineSelectRangeServerside() throws Exception {
        driver.get(concatUrl(BASEURL,
                "/timeline/TimelineFeatures?restartApplication"));
        click("ROOT::PID_Smenu#item2", 39, 7);
        click("ROOT::Root/VOverlay[0]/VMenuBar[0]#item2", 47, 4);
        click("ROOT::Root/VOverlay[1]/VMenuBar[0]#item0", 20, 6);
        compareScreen("firstWeek");

        click("ROOT::PID_Smenu#item2", 5, 6);
        click("ROOT::Root/VOverlay[0]/VMenuBar[0]#item2", 35, 3);
        click("ROOT::Root/VOverlay[1]/VMenuBar[0]#item1", 61, 9);
        compareScreen("secondWeek");

        click("ROOT::PID_Smenu#item2", 21, 11);
        click("ROOT::Root/VOverlay[0]/VMenuBar[0]#item2", 31, 4);
        click("ROOT::Root/VOverlay[1]/VMenuBar[0]#item2", 41, 15);
        compareScreen("lastWeek");
    }

    @Test
    public void testTimelineUniformBarChart() throws Exception {
        driver.get(concatUrl(BASEURL,
                "/timeline/TimelineFeatures?restartApplication"));

        click("ROOT::PID_Smenu#item0", 31, 8);
        click("ROOT::Root/VOverlay[0]/VMenuBar[0]#item4", 38, 11);
        testBenchElement(
                driver.findElement(By
                        .xpath("//button[@class='v-timeline-widget-chartmode-bar']")))
                .click(8, 9);
        compareScreen("non-uniform-bars");

        click("ROOT::PID_Smenu#item1", 31, 9);
        click("ROOT::Root/VOverlay[0]/VMenuBar[0]#item7", 79, 10);
        compareScreen("uniform-bars");
    }

    @Test
    public void testTimelineVerticalAxisNumberFormat() throws Exception {
        driver.get(concatUrl(BASEURL,
                "/timeline/TimelineFeatures?restartApplication"));

        click("ROOT::PID_Smenu#item1", 19, 12);
        click("ROOT::Root/VOverlay[0]/VMenuBar[0]#item8", 49, 5);
        click("ROOT::Root/VOverlay[1]/VMenuBar[0]#item3", 30, 4);
        compareScreen("zero-padded-value");

        click("ROOT::PID_Smenu#item0", 54, 6);
        click("ROOT::Root/VOverlay[0]/VMenuBar[0]#item4", 84, 11);
        click("ROOT::PID_Smenu#item1", 26, 6);
        click("ROOT::Root/VOverlay[0]/VMenuBar[0]#item8", 75, 8);
        click("ROOT::Root/VOverlay[1]/VMenuBar[0]#item4", 50, 7);
        compareScreen("thousand-separator");
    }
}
