package com.vaadin.addon.charts.examples.other;

import static com.vaadin.addon.charts.model.ChartType.PIE;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Lang;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
@SkipFromDemo
public class NoDataExample extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Chart with no data";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(PIE);

        Configuration conf = chart.getConfiguration();
        conf.getNoData().getPosition().setVerticalAlign(VerticalAlign.TOP);
        Lang lang = new Lang();
        lang.setNoData("Ups, there is no data to show, :'(");
        ChartOptions.get().setLang(lang);

        conf.setTitle("No data in pie chart");

        return chart;
    }

}
