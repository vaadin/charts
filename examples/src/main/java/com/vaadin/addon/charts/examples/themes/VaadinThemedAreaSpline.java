package com.vaadin.addon.charts.examples.themes;

import com.vaadin.addon.charts.examples.area.AreaSpline;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class VaadinThemedAreaSpline extends AreaSpline {

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
