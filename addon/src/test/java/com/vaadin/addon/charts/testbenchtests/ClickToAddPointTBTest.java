package com.vaadin.addon.charts.testbenchtests;

import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.addon.charts.demoandtestapp.dynamic.ClickToAddPoint;
import com.vaadin.testbench.By;
import com.vaadin.testbench.parallel.Browser;

public class ClickToAddPointTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return ClickToAddPoint.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "dynamic";
    }

    @Override
    protected void testCustomStuff() {
        WebElement findElement = driver.findElement(By.id("chart"));
        Action click = new Actions(driver).moveToElement(findElement, 200, 200)
                .click().build();

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

    @Override
    public List<DesiredCapabilities> getBrowsersToTest() {
        List<DesiredCapabilities> result = super.getBrowsersToTest();
        // FIXME: click didn't work in IE8
        result.remove(Browser.IE8.getDesiredCapabilities());
        result.remove(Browser.FIREFOX.getDesiredCapabilities());
        return result;
    }

}
