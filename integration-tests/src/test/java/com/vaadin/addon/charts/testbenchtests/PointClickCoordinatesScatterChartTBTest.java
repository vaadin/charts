/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import com.vaadin.addon.charts.examples.pointclickevent.PointClickCoordinatesScatterChart;
import com.vaadin.testbench.By;
import com.vaadin.testbench.parallel.Browser;

@Ignore("Absolute coordinates affected by Theme change")
public class PointClickCoordinatesScatterChartTBTest
        extends AbstractPointClickCoordinatesTest {

    @Override
    protected String getTestViewName() {
        return PointClickCoordinatesScatterChart.class.getSimpleName();
    }

    @Override
    protected void clickPoint() {
        WebElement element = driver
                .findElement(By.cssSelector(".highcharts-series-2 > path"));
        Point location = element.getLocation();
        Dimension size = element.getSize();
        expectedPointX = location.getX() + (size.width / 2);
        expectedPointY = location.getY() + (size.height / 2);
        super.clickPoint();
    }

    @Override
    protected void assertPointClickEventCoordinates() {
        skipBrowser("For some reason Firefox gets wrong event type.",
                Browser.FIREFOX);
        super.assertPointClickEventCoordinates();
    }

}
