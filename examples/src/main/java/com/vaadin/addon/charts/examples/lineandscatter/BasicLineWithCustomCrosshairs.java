package com.vaadin.addon.charts.examples.lineandscatter;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Crosshair;
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
        return "Basic chart with customized crosshairs on both axes";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();
        chart.setId("chart");
        Configuration config = chart.getConfiguration();
        config.setTitle("Customized crosshairs");

        Crosshair xCrossHair = new Crosshair();
        xCrossHair.setColor(SolidColor.BLACK);
        xCrossHair.setDashStyle(DashStyle.SOLID);
        xCrossHair.setWidth(10);
        xCrossHair.setZIndex(0);
        config.getxAxis().setCrosshair(xCrossHair);

        Crosshair yCrossHair = new Crosshair();
        yCrossHair.setColor(new SolidColor("#880000"));
        yCrossHair.setDashStyle(DashStyle.DOT);
        yCrossHair.setWidth(5);
        yCrossHair.setZIndex(1);
        config.getyAxis().setCrosshair(yCrossHair);

        ListSeries ls = new ListSeries();
        ls.setName("Data");
        ls.setData(29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4,
                194.1, 95.6, 54.4);

        config.setSeries(ls);

        chart.drawChart(config);

        return chart;
    }

}
