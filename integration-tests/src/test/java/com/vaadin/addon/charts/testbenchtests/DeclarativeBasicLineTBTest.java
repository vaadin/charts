package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.declarative.DeclarativeBasicLine;

public class DeclarativeBasicLineTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return DeclarativeBasicLine.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "declarative";
    }

}
