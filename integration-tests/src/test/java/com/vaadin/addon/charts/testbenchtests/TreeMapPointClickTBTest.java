package com.vaadin.addon.charts.testbenchtests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.addon.charts.PointClickEvent;
import com.vaadin.addon.charts.examples.other.TreeMapPointClick;
import com.vaadin.testbench.By;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.parallel.Browser;
import com.vaadin.ui.Component;

public class TreeMapPointClickTBTest extends AbstractParallelTest {

    @Override
    protected String getTestViewName() {
        return TreeMapPointClick.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }

    private void openTestUI() {
        driver.get(getTestUrl());
        waitUntilChartRendered();
    }

    @Test
    public void pointClick_occured_eventIsFired() {
        openTestUI();

        WebElement treeMapDataPoint = findTreeMapDataPoint();

        click(treeMapDataPoint);

        assertFirstHistoryEventIsType(PointClickEvent.class);
    }

    private WebElement findTreeMapDataPoint() {
        return driver.findElement(By
                .cssSelector(".highcharts-levelGroup-2 > rect"));
    }

    private void assertFirstHistoryEventIsType(
            Class<? extends Component.Event> expectedEvent) {
        LabelElement lastEvent = $(LabelElement.class).first();
        String eventHistory = lastEvent.getText();
        Assert.assertNotNull(eventHistory);
        String eventType = eventHistory.split(":")[0];
        Assert.assertEquals(expectedEvent.getSimpleName(), eventType);
    }

    private void click(WebElement secondCheckBox) {
        new Actions(driver).click(secondCheckBox).build().perform();
        getTestBenchCommandExecutor().waitForVaadin();
    }

}
