package com.vaadin.addon.charts.testbenchtests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.addon.charts.examples.lineandscatter.BasicLineGettingMousePointerPosition;
import com.vaadin.testbench.By;
import com.vaadin.testbench.annotations.BrowserConfiguration;
import com.vaadin.testbench.parallel.Browser;

public class PixelCoordinatesTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return BasicLineGettingMousePointerPosition.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

    @Override
    protected void testCustomStuff() {
        WebElement findElement = driver.findElement(By.id("chart"));
        Action click;
        // Point click, needs to hover on point before click
        // Note, coordinates are for the point, not for the click, so exactly
        // 80,315 should not be expected in UI
        click = new Actions(driver).moveToElement(findElement, 84, 315).build();
        click.perform();
        waitForVaadin();
        click = new Actions(driver).moveToElement(findElement, 85, 315).build();
        click.perform();
        waitForVaadin();
        click = new Actions(driver).click().build();
        click.perform();

        // Chart click
        click = new Actions(driver).moveToElement(findElement, 100, 100)
                .click().build();

        click.perform();
        waitForVaadin();
    }

    @Override
    @BrowserConfiguration
    public List<DesiredCapabilities> getBrowsersToTest() {
        List<DesiredCapabilities> allBrowsers = super.getBrowsersToTest();
        allBrowsers.remove(Browser.FIREFOX.getDesiredCapabilities());
        return allBrowsers;
    }

}
