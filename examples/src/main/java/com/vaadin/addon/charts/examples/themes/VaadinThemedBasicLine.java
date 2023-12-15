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
import com.vaadin.addon.charts.examples.lineandscatter.BasicLine;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class VaadinThemedBasicLine extends BasicLine {

    @Override
    public String getDescription() {
        return "Vaadin themed: " + super.getDescription();
    }

    @Override
    protected Component getChart() {
        // Vaadin theme is on by default
        // ChartTheme.get().setTheme(new VaadinTheme());
        // disable some customizations from super
        Chart chart = (Chart) super.getChart();
        Legend legend = chart.getConfiguration().getLegend();
        Style itemHoverStyle = new Style();
        legend.setItemHoverStyle(itemHoverStyle);
        legend.setBorderWidth(null);

        return chart;
    }

}
