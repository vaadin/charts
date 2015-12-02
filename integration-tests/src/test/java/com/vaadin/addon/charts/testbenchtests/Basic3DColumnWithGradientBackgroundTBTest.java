package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.Basic3DColumnWithGradientBackground;

public class Basic3DColumnWithGradientBackgroundTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return Basic3DColumnWithGradientBackground.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
