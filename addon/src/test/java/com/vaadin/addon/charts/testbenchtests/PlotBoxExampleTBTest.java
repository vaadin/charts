package com.vaadin.addon.charts.testbenchtests;

import org.openqa.selenium.By;

import com.vaadin.addon.charts.demoandtestapp.other.PlotBoxExample;


public class PlotBoxExampleTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return PlotBoxExample.class.getName();
    }
    
    @Override
    protected void testCustomStuff() {
        super.testCustomStuff();
        driver.findElement(By.xpath("//input")).click();
    }

}
