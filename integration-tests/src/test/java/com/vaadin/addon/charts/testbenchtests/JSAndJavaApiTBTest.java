package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.other.JSAndJavaApi;

public class JSAndJavaApiTBTest extends AbstractSimpleScreenShotTestBenchTest {

    @Override
    protected String getTestViewName() {
        return JSAndJavaApi.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
