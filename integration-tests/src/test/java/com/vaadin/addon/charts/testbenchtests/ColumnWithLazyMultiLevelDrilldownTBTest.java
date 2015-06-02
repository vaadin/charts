package com.vaadin.addon.charts.testbenchtests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.addon.charts.examples.columnandbar.ColumnWithLazyMultiLevelDrilldown;
import com.vaadin.testbench.By;
import com.vaadin.testbench.elements.LabelElement;

public class ColumnWithLazyMultiLevelDrilldownTBTest extends
        AbstractParallelTest {

    @Override
    protected String getTestViewName() {
        return ColumnWithLazyMultiLevelDrilldown.class.getSimpleName();
    }

    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

        waitBetweenShots();

        WebElement findElement = driver.findElement(By.id("chart"));
        Action moveAndClick = new Actions(driver)
                .moveToElement(findElement, 230, 250).click()
                .moveToElement(findElement, 0, 0).build();
        moveAndClick.perform();
        assertLogText("DrilldownEvent: Latin America and Carribean");

        moveAndClick = new Actions(driver).moveToElement(findElement, 120, 255)
                .click().moveToElement(findElement, 0, 0).build();
        moveAndClick.perform();
        assertLogText("DrilldownEvent: Costa Rica");

        moveAndClick = new Actions(driver).moveToElement(findElement, 630, 100)
                .click().moveToElement(findElement, 0, 0).build();
        moveAndClick.perform();
        assertLogText("ChartDrillupEvent");

    }

    private void assertLogText(String text) {
        sleep(1000);
        assertTrue(
                String.format("Couldn't find text '%s' from the log.", text),
                logContainsText(text));
    }

    private boolean logContainsText(String string) {
        LabelElement label = $(LabelElement.class).first();
        if (label.getText().contains(string)) {
            return true;
        }

        return false;
    }

    @Override
    protected String getPackageName() {
        return "columnandbar";
    }

}
