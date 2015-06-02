package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.timeline.SanityCheck;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TimelineSanityCheckTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected void configBrowser() {
        // We need a bigger viewport to fit everything.
        testBench.resizeViewPortTo(1212, 605);
    }

    @Override
    protected String getTestViewName() {
        return SanityCheck.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "timeline";
    }

    @Override
    protected void waitUntilChartRendered() {
        new WebDriverWait(driver, 120).until(ExpectedConditions
                .presenceOfElementLocated(com.vaadin.testbench.By
                        .className("v-timeline")));
    }

}
