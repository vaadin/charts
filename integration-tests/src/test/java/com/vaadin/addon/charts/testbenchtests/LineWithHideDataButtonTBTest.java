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

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.addon.charts.examples.dynamic.LineWithHideDataButton;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.LabelElement;

public class LineWithHideDataButtonTBTest extends AbstractParallelTest {


    @Override
    protected String getTestViewName() {
        return LineWithHideDataButton.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "dynamic";
    }

    private void openTestUI() {
        driver.get(getTestUrl());
        waitUntilChartRendered();
    }

    @Test
    public void hideData_ocurred_extremesChanged() {
        openTestUI();
        
        ButtonElement showHideSeriesButton = $(ButtonElement.class).id("showHideSeriesButton");
        LabelElement extremesLabel = $(LabelElement.class).id("extremesLabel");
        
        showHideSeriesButton.click();
        Assert.assertEquals("Min 0.0 Max: 0.0", extremesLabel.getText());
        showHideSeriesButton.click();
        Assert.assertNotEquals("Min 0.0 Max: 0.0",  extremesLabel.getText());
    }

}
