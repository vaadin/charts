package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.testbench.By;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.VerticalLayoutElement;
import com.vaadin.testbench.parallel.Browser;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class AbstractPointClickCoordinatesTest
        extends AbstractParallelTest {

    protected int expectedPointX = 450;
    protected int expectedPointY = 300;

    protected int expectedChartX = 650;
    protected int expectedChartY = 200;

    @Override
    protected String getPackageName() {
        return "pointclickevent";
    }

    private void openTestUI() {
        driver.get(getTestUrl());
        waitUntilChartRendered();
    }

    @Test
    public void pointClick_occured_correctAbsoluteCoordinates() {
        openTestUI();

        clickPoint();

        assertPointClickEventCoordinates();
    }

    @Test
    public void chartClick_occured_correctAbsoluteCoordinates() {
        openTestUI();

        clickChart();

        assertChartClickEventCoordinates();
    }

    protected void assertPointClickEventCoordinates() {
        LabelElement lastEvent = $(LabelElement.class).get(1);
        String eventHistory = lastEvent.getText();
        Assert.assertNotNull("No PointClickEvent when there should have been.",
                eventHistory);
        String[] eventTokens = eventHistory.split(" ");
        Assert.assertEquals("Unexpected contents: " + eventHistory, 6,
                eventTokens.length);
        Assert.assertEquals("Unexpected event,", "PointClickEvent:",
                eventTokens[0]);
        Assert.assertEquals("PointClickEvent absoluteX got unexpected value,",
                String.valueOf(expectedPointX), eventTokens[2]);
        Assert.assertEquals("PointClickEvent absoluteY got unexpected value,",
                String.valueOf(expectedPointY), eventTokens[5]);
    }

    protected void assertChartClickEventCoordinates() {
        LabelElement lastEvent = $(LabelElement.class).get(1);
        String eventHistory = lastEvent.getText();
        Assert.assertNotNull("No ChartClickEvent when there should have been.",
                eventHistory);
        String[] eventTokens = eventHistory.split(" ");
        Assert.assertEquals("Unexpected contents: " + eventHistory, 6,
                eventTokens.length);
        Assert.assertEquals("Unexpected event,", "ChartClickEvent:",
                eventTokens[0]);
        WebElement chart = findElement(By.className("vaadin-chart"));
        Assert.assertEquals("ChartClickEvent absoluteX got unexpected value,",
                String.valueOf(chart.getLocation().getX() + expectedChartX),
                eventTokens[2]);
        Assert.assertEquals("ChartClickEvent absoluteY got unexpected value,",
                String.valueOf(chart.getLocation().getY() + expectedChartY),
                eventTokens[5]);
    }

    protected void clickPoint() {
        VerticalLayoutElement layout = $(VerticalLayoutElement.class).first();
        new Actions(driver)
                .moveToElement(layout, expectedPointX, expectedPointY).click()
                .build().perform();
        getTestBenchCommandExecutor().waitForVaadin();
    }

    private void clickChart() {
        WebElement chart = findElement(By.className("vaadin-chart"));
        new Actions(driver).moveToElement(chart, expectedChartX, expectedChartY)
                .click().build().perform();
        getTestBenchCommandExecutor().waitForVaadin();
    }
}
