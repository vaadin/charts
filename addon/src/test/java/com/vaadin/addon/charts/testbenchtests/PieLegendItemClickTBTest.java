package com.vaadin.addon.charts.testbenchtests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.vaadin.addon.charts.demoandtestapp.pie.PieWithLegend;

public class PieLegendItemClickTBTest extends AbstractTestBenchTest {

    @Test
    public void test() {
        startBrowser();
        driver.navigate().to(BASEURL + PieWithLegend.class.getName());

        WebElement ie = getLegendItem("IE");
        ie.click();
        try {
            getNotification("Legend item click");
        } catch (NoSuchElementException ex) {
            Assert.fail("The click notification was not displayed");
        }
    }

    private WebElement getLegendItem(String name) {
        return driver.findElement(By
                .xpath("//*[contains(@class, 'highcharts-legend-item')]"
                        + "//*[contains(text(), '" + name + "')]"));
    }

    private WebElement getNotification(String text) {
        return driver.findElement(By
                .xpath("//*[contains(@class, 'v-Notification')]"
                        + "//*[contains(text(), '" + text + "')]"));
    }
}
