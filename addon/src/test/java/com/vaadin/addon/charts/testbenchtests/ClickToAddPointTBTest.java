package com.vaadin.addon.charts.testbenchtests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.addon.charts.demoandtestapp.dynamic.ClickToAddPoint;
import com.vaadin.testbench.By;

public class ClickToAddPointTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        String simpleName = ClickToAddPoint.class.getSimpleName();
        return simpleName;
    }

    @Override
    protected String getPackageName() {
        return "dynamic";
    }

    @Override
    protected void testCustomStuff() {
        WebElement findElement = driver.findElement(By
                .id("chart"));
        new Actions(driver).moveToElement(findElement, 200, 200).click()
                .perform();

    }

}
