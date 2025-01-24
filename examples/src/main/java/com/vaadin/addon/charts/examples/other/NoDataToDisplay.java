/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.examples.other;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.ui.Component;

@SkipFromDemo
public class NoDataToDisplay extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Test with No data displayed.";
    }

    @Override
    protected Component getChart() {
        return new Chart(ChartType.COLUMN);
    }

}