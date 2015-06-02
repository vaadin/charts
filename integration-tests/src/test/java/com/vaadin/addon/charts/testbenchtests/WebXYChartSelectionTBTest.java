package com.vaadin.addon.charts.testbenchtests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.addon.charts.examples.dynamic.WebXYChartSelection;
import com.vaadin.testbench.By;

public class WebXYChartSelectionTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return WebXYChartSelection.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "dynamic";
    }

    @Override
    protected void testCustomStuff() {
        WebElement findElement = driver.findElement(By.id("chart"));
        Action click = new Actions(driver).moveToElement(findElement, 100, 100)
                .clickAndHold().moveByOffset(200, 50).release().build();
        click.perform();
        sleep(1000);
    }

}
