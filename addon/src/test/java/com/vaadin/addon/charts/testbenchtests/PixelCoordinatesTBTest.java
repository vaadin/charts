package com.vaadin.addon.charts.testbenchtests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.addon.charts.demoandtestapp.lineandscatter.BasicLineGettingMousePointerPosition;
import com.vaadin.testbench.By;

public class PixelCoordinatesTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        String simpleName = BasicLineGettingMousePointerPosition.class
                .getName();
        return simpleName;
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
        sleep(1000);
        click = new Actions(driver).moveToElement(findElement, 85, 315).build();
        click.perform();
        sleep(1000);
        click = new Actions(driver).click().build();
        click.perform();

        // Chart click
        click = new Actions(driver).moveToElement(findElement, 100, 100)
                .click().build();

        click.perform();
        sleep(100);

    }
}
