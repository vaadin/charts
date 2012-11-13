package com.vaadin.addon.charts.demoandtestapp.themes;

import com.vaadin.addon.charts.ChartTheme;
import com.vaadin.addon.charts.demoandtestapp.columnandbar.BarWithNegativeStack;
import com.vaadin.addon.charts.themes.GrayTheme;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class GrayThemedBarChart extends BarWithNegativeStack {

    @Override
    public String getDescription() {
        return "Gray themed: " + super.getDescription();
    }

    @Override
    protected Component getChart() {
        ChartTheme.get().setTheme(new GrayTheme());
        return super.getChart();
    }

}
