/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.testbenchtests;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vaadin.addon.charts.examples.columnandbar.ColumnWithMultiLevelDrilldownDuplicatePointName;

public class ColumnWithMultiLevelDrilldownDuplicatePointNameTBTest
        extends AbstractParallelTest {

    private static final String DRILLDOWN_LABEL_SELECTOR = ".highcharts-drilldown-axis-label";
    private static final String DRILLDOWN_LABEL_SELECTOR_INDEX = DRILLDOWN_LABEL_SELECTOR
            + ":nth-of-type(%s)";

    @Override
    protected String getTestViewName() {
        return ColumnWithMultiLevelDrilldownDuplicatePointName.class
                .getSimpleName();
    }

    @Test
    @Ignore  // TODO: actually fix this test..
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

        waitForVaadin();

        // Click first point
        getPointLabel(1).click();
        
        // Assert point with duplicate name is visible
        waitUntil(textToBe(getSelectorForPointLabel(3), "Switzerland"));

        // Drill up
        findElement(cssSelector(".highcharts-button")).click();
        waitUntil(numberOfElementsToBe(getSelectorForPointLabels(), 6));

        // Click second point
        getPointLabel(2).click();

        // Assert only 4 points
        waitUntil(numberOfElementsToBe(getSelectorForPointLabels(), 4));
        
        // Assert point with duplicate name is visible
        waitUntil(textToBe(getSelectorForPointLabel(3), "Switzerland"));
    }

    private WebElement getPointLabel(int index) {
        return findElement(getSelectorForPointLabel(index));
    }

    private By getSelectorForPointLabels() {
        return cssSelector(DRILLDOWN_LABEL_SELECTOR);
    }

    private By getSelectorForPointLabel(int index) {
        return cssSelector(
                String.format(DRILLDOWN_LABEL_SELECTOR_INDEX, index));
    }

    @Override
    protected String getPackageName() {
        return "columnandbar";
    }

}
