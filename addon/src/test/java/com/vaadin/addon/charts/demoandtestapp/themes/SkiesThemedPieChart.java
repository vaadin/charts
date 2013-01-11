package com.vaadin.addon.charts.demoandtestapp.themes;

import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.demoandtestapp.pie.PieChart;
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
