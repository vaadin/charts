package com.vaadin.addon.charts.testbenchtests;

import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.addon.charts.demoandtestapp.lineandscatter.SplineWithItemLabels;
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

    @Override
    public List<DesiredCapabilities> getBrowsersToTest() {
        List<DesiredCapabilities> result = super.getBrowsersToTest();
        result.remove(Browser.IE8.getDesiredCapabilities());
        return result;
    }

}
