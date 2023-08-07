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

import org.junit.Ignore;
import org.openqa.selenium.By;

import com.vaadin.addon.charts.examples.other.BoxPlotExample;

@Ignore("Absolute coordinates affected by Theme change")
public class BoxPlotExampleTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return BoxPlotExample.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }

    @Override
    protected void testCustomStuff() {
        super.testCustomStuff();
        driver.findElement(By.xpath("//input")).click();
    }

}
