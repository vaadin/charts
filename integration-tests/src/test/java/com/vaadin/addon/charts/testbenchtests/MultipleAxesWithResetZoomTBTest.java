package com.vaadin.addon.charts.testbenchtests;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.addon.charts.examples.combinations.MultipleAxesWithResetZoom;
import com.vaadin.testbench.By;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.parallel.Browser;

public class MultipleAxesWithResetZoomTBTest extends AbstractParallelTest {

    @Test
    public void chartIsZoomed_zoomResetFromServer_zoomIsReset()
            throws IOException, AssertionError {
        driver.get(getTestUrl());

        zoomChart();
        waitForDynamicChanges();
        captureAndCompare("zoomed");

        $(ButtonElement.class).first().click();
        waitForDynamicChanges();
        captureAndCompare("zoom-reset");
    }

    private void zoomChart() {
        List<WebElement> points = driver.findElements(By
                .cssSelector(".highcharts-series  > rect"));
        Actions builder = new Actions(driver);
        Action dragAndDrop = builder.clickAndHold(points.get(3))
                .moveToElement(points.get(9)).release(points.get(9)).build();
        dragAndDrop.perform();
    }

    @Override
    protected String getTestViewName() {
        return MultipleAxesWithResetZoom.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "combinations";
    }

}
