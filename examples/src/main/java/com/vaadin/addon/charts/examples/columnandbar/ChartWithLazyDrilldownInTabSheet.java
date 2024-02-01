/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.examples.columnandbar;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;

@SkipFromDemo
public class ChartWithLazyDrilldownInTabSheet
        extends AbstractVaadinChartExample {

    /**
     * 
     * Test UI for #483.
     * 
     * To reproduce issue:
     * <ul>
     * <li>Click on IE</li>
     * <li>Change to second tab</li>
     * <li>Change to first tab</li>
     * <li>Click on opera</li>
     * </ul>
     */
    @Override
    protected Component getChart() {
        TabSheet tabs = new TabSheet();
        tabs.setSizeFull();
        Chart chart = (Chart) new ColumnWithNativeLazyDrilldown().getChart();
        tabs.addTab(chart, "First tab with chart");
        Label label = new Label("second tab content");
        tabs.addTab(label, "Other tab");
        return tabs;
    }
}
