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
