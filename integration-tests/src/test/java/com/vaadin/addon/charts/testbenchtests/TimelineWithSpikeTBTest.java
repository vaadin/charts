package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.timeline.TimelineWithSpike;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TimelineWithSpikeTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return TimelineWithSpike.class.getSimpleName();
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
