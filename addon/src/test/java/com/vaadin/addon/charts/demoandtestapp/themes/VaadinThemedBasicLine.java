package com.vaadin.addon.charts.demoandtestapp.themes;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.lineandscatter.BasicLine;
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
