/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
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
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.ui.Component;

public class LinesWithComplexHtmlTooltip extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Tooltip.setUseHTML usage exable.";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();
        chart.setHeight("450px");
        chart.setWidth("100%");

        Tooltip tooltip = new Tooltip();
        tooltip.setShared(true);
        tooltip.setUseHTML(true);
        tooltip.setHeaderFormat("<small>{point.key}</small><table>");
        tooltip.setPointFormat("<tr><td style=\"color: {series.color}\">{series.name}: </td><td style=\"text-align: right\"><b>{point.y} EUR</b></td></tr>");
        tooltip.setFooterFormat("</table>");

        Configuration configuration = chart.getConfiguration();
        configuration.setTitle("Complex tooltip");
        configuration.setTooltip(tooltip);

        configuration.getxAxis().setCategories("Jan", "Feb", "Mar", "Apr",
                "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

        ListSeries ls = new ListSeries();
        ls.setName("Short");
        ls.setData(29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4,
                194.1, 95.6, 54.4);
        configuration.addSeries(ls);
        ls = new ListSeries();
        ls.setName("Long named series");
        Number[] data = new Number[] { 129.9, 171.5, 106.4, 129.2, 144.0,
                176.0, 135.6, 148.5, 216.4, 194.1, 195.6, 154.4 };
        for (int i = 0; i < data.length / 2; i++) {
            Number number = data[i];
            data[i] = data[data.length - i - 1];
            data[data.length - i - 1] = number;
        }

        ls.setData(data);
        configuration.addSeries(ls);

        chart.drawChart(configuration);
        return chart;
    }

}
