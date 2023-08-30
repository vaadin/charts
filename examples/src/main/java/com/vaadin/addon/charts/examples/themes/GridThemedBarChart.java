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

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.examples.columnandbar.BarWithNegativeStack;
import com.vaadin.addon.charts.themes.GridTheme;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class GridThemedBarChart extends BarWithNegativeStack {

    @Override
    public String getDescription() {
        return "Grid themed: " + super.getDescription();
    }

    @Override
    protected Component getChart() {
        ChartOptions.get().setTheme(new GridTheme());
        Chart chart = (Chart) super.getChart();
        // chart.getConfiguration().getyAxis().setMinorTickInterval("auto");
        return chart;
    }

}
