package com.vaadin.addon.charts.testbenchtests;

import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.addon.charts.examples.lineandscatter.SplineWithItemLabels;
import com.vaadin.testbench.parallel.Browser;

public class SplineWithItemLabelsTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return SplineWithItemLabels.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
