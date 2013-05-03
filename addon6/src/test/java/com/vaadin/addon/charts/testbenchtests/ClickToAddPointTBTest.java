package com.vaadin.addon.charts.testbenchtests;

import junit.framework.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
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
        WebElement findElement = driver.findElement(By.id("chart"));
        Action click = new Actions(driver)
                .moveToElement(findElement, 200, 200).click().build();
        
        click.perform();
        
        sleep(1000); // FIXME investigate why randomly fails without this
        
        Assert.assertTrue(eventLogText().startsWith("Added"));

        click.perform();
        
        sleep(1000); // FIXME investigate why randomly fails without this
        
        Assert.assertTrue(eventLogText().startsWith("Removed"));
    }

    private String eventLogText() {
        return driver.findElement(By.id("lastAction")).getText();
    }

}
