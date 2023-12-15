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
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.StepType;
import com.vaadin.ui.Component;

public class StepLines extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Basic Line With Data Labels";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();

        Configuration configuration = chart.getConfiguration();
        configuration.setTitle("Step lines");

        configuration.getxAxis().setCategories("Jan", "Feb", "Mar", "Apr",
                "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

        ListSeries ls = new ListSeries();
        ls.setName("Right");
        ls.setData(1, 2, 3, 4, null, 6, 7, null, 9);
        PlotOptionsLine options = new PlotOptionsLine();
        options.setStep(StepType.RIGHT);
        ls.setPlotOptions(options);
        configuration.addSeries(ls);

        ls = new ListSeries();
        ls.setName("Center");
        ls.setData(5, 6, 7, 8, null, 10, 11, null, 13);
        options = new PlotOptionsLine();
        options.setStep(StepType.CENTER);
        ls.setPlotOptions(options);
        configuration.addSeries(ls);

        ls = new ListSeries();
        ls.setName("Left");
        ls.setData(9, 10, 11, 12, null, 14, 15, null, 17);
        options = new PlotOptionsLine();
        options.setStep(StepType.LEFT);
        ls.setPlotOptions(options);
        configuration.addSeries(ls);

        chart.drawChart(configuration);
        return chart;
    }

}
