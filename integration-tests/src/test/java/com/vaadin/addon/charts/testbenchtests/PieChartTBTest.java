package com.vaadin.addon.charts.testbenchtests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.addon.charts.examples.pie.PieChart;

public class PieChartTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return PieChart.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "pie";
    }

    @Override
    protected void testCustomStuff() {
        super.testCustomStuff();
        // Ensure animation has finished before clicking
        waitForVaadin();

        WebElement chart = driver.findElement(By
                .xpath("//div[contains(@class, 'vaadin-chart')]"));
        Action click = new Actions(driver).moveToElement(chart, 400 + 20, 200)
                .click().build();

        click.perform();

        waitForVaadin();

        boolean containsFirefox = driver
                .findElement(By.className("v-Notification")).getText()
                .contains("Firefox");
        Assert.assertTrue(containsFirefox);

    }
}
