package com.vaadin.addon.charts.demoandtestapp.lineandscatter;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.CrosshairStyle;
import com.vaadin.addon.charts.model.DashStyle;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

/**
 * Shows a line diagram with custom crosshair styles on both axes.
 *
 */
public class BasicLineWithCustomCrosshairs extends AbstractVaadinChartExample {

    private static final long serialVersionUID = 20141112;

    @Override
    public String getDescription() {
        return "Basic chart with customised crosshairs on both axes";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();

        Configuration config = chart.getConfiguration();

        config.getTooltip().setCrosshairs(
                new CrosshairStyle(10, SolidColor.BLACK, DashStyle.SOLID, 0),
                new CrosshairStyle(5, "#880000", DashStyle.DOT, 1));

        ListSeries ls = new ListSeries();
        ls.setName("Data");
        ls.setData(29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4,
                194.1, 95.6, 54.4);

        config.setSeries(ls);

        chart.drawChart(config);

        return chart;
    }

}
