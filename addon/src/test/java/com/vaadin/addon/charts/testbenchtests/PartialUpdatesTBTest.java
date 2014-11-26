package com.vaadin.addon.charts.testbenchtests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vaadin.addon.charts.demoandtestapp.PartialUpdateConflicts;

public class PartialUpdatesTBTest extends AbstractParallelTest {

    @Test
    public void test() {

        driver.get(getTestUrl() + "?debug");

        WebElement b1 = driver.findElement(By.id("b1"));
        b1.click();
        b1.click();
        verifyNoError();
        WebElement b2 = driver.findElement(By.id("b2"));
        b2.click();
        b2.click();
        verifyNoError();
    }

    private void verifyNoError() {
        Assert.assertEquals("Error was present in the client!", 0, driver
                .findElements(By.className("v-Notification")).size());
    }

    @Override
    protected String getTestViewName() {
        return PartialUpdateConflicts.class.getSimpleName();
    }
}
