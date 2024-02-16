/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.examples.other;


import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.examples.themes.CustomTheme;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;

@SkipFromDemo
public class CustomThemeUI extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Custom theme test with chart border color.";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();
        chart.setHeight("450px");
        chart.setWidth("100%");

        ChartOptions.get().setTheme(new CustomTheme());

        Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.LINE);
        configuration.getChart().setMarginRight(130);
        configuration.getChart().setMarginBottom(25);

        configuration.getTitle().setText("Monthly Average Temperature");
        configuration.getSubTitle().setText("Source: WorldClimate.com");

        configuration.getxAxis().setCategories(
                new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
                        "Aug", "Sep", "Oct", "Nov", "Dec" });

        YAxis yAxis = configuration.getyAxis();
        yAxis.setMin(-5d);
        yAxis.setTitle(new AxisTitle("Temperature (°C)"));
        yAxis.getTitle().setAlign(VerticalAlign.MIDDLE);

        configuration
                .getTooltip()
                .setFormatter(
                        "'<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C'");

        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setDataLabels(new DataLabels(true));
        configuration.setPlotOptions(plotOptions);


        ListSeries ls = new ListSeries();
        ls.setName("Tokyo");
        ls.setData(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3,
                13.9, 9.6);
        configuration.addSeries(ls);

        chart.drawChart(configuration);
        return chart;
    }

}
