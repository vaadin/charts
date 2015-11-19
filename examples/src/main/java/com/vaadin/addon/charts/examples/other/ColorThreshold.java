package com.vaadin.addon.charts.examples.other;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.PlotOptionsAreaRange;
import com.vaadin.addon.charts.model.PlotOptionsSeries;
import com.vaadin.ui.Component;

public class ColorThreshold extends AreaRange {

    @Override
    protected Component getChart() {
        Chart chart = (Chart) super.getChart();
        PlotOptionsAreaRange plotOptions = new PlotOptionsAreaRange();
        // make "value" under -5 blue. Default threshold value is 0
        plotOptions.setNegativeColor(getThemeColors()[1]);
        plotOptions.setThreshold(-5);
        chart.getConfiguration().setPlotOptions(plotOptions);
        return chart;
    }

}
