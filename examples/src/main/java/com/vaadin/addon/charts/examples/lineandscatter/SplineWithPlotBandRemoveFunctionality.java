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
package com.vaadin.addon.charts.examples.lineandscatter;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.PlotBand;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

@SkipFromDemo
public class SplineWithPlotBandRemoveFunctionality extends
SplineWithPlotBands {

    @SuppressWarnings("deprecation")
    @Override
    protected Component getChart() {
        final Chart chart = (Chart) super.getChart();

        final Button removePlotBand = new Button("Remove PlotBands");
        removePlotBand.setId("vaadin-button");
        removePlotBand.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                Configuration configuration = chart.getConfiguration();
                YAxis axis = configuration.getyAxis();
                if (axis.getPlotBands() == null
                        || axis.getPlotBands().length == 0) {
                    createPlotBands(chart.getConfiguration().getyAxis());
                    removePlotBand.setCaption("Remove PlotBands");
                } else {
                    chart.getConfiguration().getyAxis()
                            .setPlotBands(new PlotBand[] {});
                    removePlotBand.setCaption("Restore PlotBands");
                }
                chart.drawChart(configuration);
            }
        });

        VerticalLayout verticalLayout = new VerticalLayout(removePlotBand, chart);
        verticalLayout.setSpacing(false);
        verticalLayout.setMargin(false);
        return verticalLayout;
    }


}
