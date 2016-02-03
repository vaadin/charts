package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.declarative.BasicLine;

public class DeclarativeBasicLineTBTest extends AbstractSimpleScreenShotTestBenchTest {

    protected String getTestViewName() {
        String simpleName = BasicLine.class.getSimpleName();
        return simpleName;
    }

    @Override
    protected String getPackageName() {
        return "declarative";
    }

}
