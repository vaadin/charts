package com.vaadin.addon.charts.demoandtestapp.themes;

import com.vaadin.addon.charts.demoandtestapp.area.PercentageArea;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class VaadinThemedPercentageArea extends PercentageArea {
    @Override
    public String getDescription() {
        return "Vaadin themed: " + super.getDescription();
    }

    @Override
    protected Component getChart() {
        // Vaadin theme is on by default
        // ChartTheme.get().setTheme(new VaadinTheme());
        return super.getChart();
    }
}
