package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.demoandtestapp.PartialUpdateConflicts;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class PartialUpdates extends
        AbstractTestBenchTest {

    @Test
    public void test() {
        startBrowser();
        driver.navigate().to(BASEURL + PartialUpdateConflicts.class.getName() + "?debug");
        
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
        try {
            driver.findElement(By.className("v-Notification"));
        } catch (NoSuchElementException ex) {
            return;
        }
        Assert.fail("Error was present in the client!");
    }
}
