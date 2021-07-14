package com.vaadin.addon.charts.testbenchtests;

import java.io.IOException;

import org.junit.Test;

import com.vaadin.addon.charts.examples.themes.ChangingThemes;
import com.vaadin.testbench.elements.ButtonElement;

public class ChangingThemesTBTest extends AbstractParallelTest {

    @Test
    public void test() throws IOException, AssertionError {
        driver.get(getTestUrl());

        waitForVaadin();

        captureAndCompare("1-start");
        $(ButtonElement.class).id("vaadin-button").click();
        waitForVaadin();
        waitForDynamicChanges();
        captureAndCompare("2-vaadin");

        $(ButtonElement.class).id("grid-button").click();
        waitForVaadin();
        waitForDynamicChanges();
        captureAndCompare("3-grid");

        $(ButtonElement.class).id("gray-button").click();
        waitForVaadin();
        waitForDynamicChanges();
        captureAndCompare("5-gray");

        $(ButtonElement.class).id("vl-button").click();
        waitForVaadin();
        waitForDynamicChanges();
        captureAndCompare("6-valoLight");

        $(ButtonElement.class).id("vd-button").click();
        waitForVaadin();
        waitForDynamicChanges();
        captureAndCompare("7-valoDark");

    }

    @Override
    protected String getTestViewName() {
        return ChangingThemes.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "themes";
    }

}
