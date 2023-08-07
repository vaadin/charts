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
package com.vaadin.addon.charts.examples.themes;

import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.examples.pie.PieChart;
import com.vaadin.addon.charts.themes.SkiesTheme;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class SkiesThemedPieChart extends PieChart {

    @Override
    public String getDescription() {
        return "Skies themed: " + super.getDescription();
    }

    @Override
    protected Component getChart() {
        ChartOptions.get().setTheme(new SkiesTheme());
        return super.getChart();
    }

}
