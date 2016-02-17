package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.lineandscatter.BasicLineWithCustomCrosshairLabels;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BasicLineWithCustomCrosshairLabelsTBTest extends AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        return BasicLineWithCustomCrosshairLabels.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }


    @Override
    protected void testCustomStuff()  {
        WebElement findElement = driver.findElement(com.vaadin.testbench.By.id("chart"));
        //Need to move cursor twice, otherwise the tooltip is in the top left corner
        new Actions(driver).moveToElement(findElement, 600, 200).perform();
        new Actions(driver).moveToElement(findElement, 600, 200).click().perform();
        //need to add a delay otherwise tooltip might be not rendered
        waitForDynamicChanges();
    }
}
