package com.vaadin.addon.charts.examples.lineandscatter;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 *
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 *
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Crosshair;
import com.vaadin.addon.charts.model.DashStyle;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

@SkipFromDemo
public class BasicLineWithCustomCrosshairLabels extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Basic chart with customized crosshair label";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();
        chart.setTimeline(true);
        chart.setId("chart");
        Configuration config = chart.getConfiguration();
        config.setTitle("Customized crosshairs");
        config.getChart().setAnimation(false);
        Crosshair xCrossHair = new Crosshair();
        xCrossHair.getLabel().setEnabled(true);
        xCrossHair.getLabel().setBackgroundColor(new SolidColor("#ff0000"));
        xCrossHair.getLabel().setBorderColor(new SolidColor("#ff00ff"));
        xCrossHair.getLabel().setBorderWidth(3);
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
