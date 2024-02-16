/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.lineandscatter.SplineWithSymbols;

@Ignore // This test is broken
public class SplineWithSymbolsTBTest extends
        AbstractSimpleScreenShotTestBenchTest {

    
    @Override
    protected String getTestViewName() {
        return SplineWithSymbols.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "lineandscatter";
    }

}
