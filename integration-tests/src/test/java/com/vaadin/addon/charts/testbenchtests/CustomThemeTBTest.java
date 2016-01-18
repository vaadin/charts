package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.CustomThemeUI;

public class CustomThemeTBTest extends AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        String simpleName = CustomThemeUI.class.getSimpleName();
        return simpleName;
    }

    @Override
    protected String getPackageName() {
        return "other";
    }

}
