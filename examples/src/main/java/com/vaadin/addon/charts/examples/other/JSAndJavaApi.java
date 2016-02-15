package com.vaadin.addon.charts.examples.other;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class JSAndJavaApi extends AbstractVaadinChartExample {

    /**
     * An example Highchart configuration copy pasted from jsfiddle
     */
    private static final String JSON_CONF = "{\n"
            + "chart: {                                              "
            // renderTo is not supported, will be overridden by library if set
            // + "    renderTo: 'container',                            "
            + "    type: 'line'                                      "
            + "},                                                    "
            + "title: {                                              "
            + "    text: 'Configured using JSON',                    "
            + "},                                                    "
            + "xAxis: {                                              "
            + "    categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'] "
            + "},                                                    "
            + "yAxis: {                                              "
            + "},                                                    "
            + "legend: {                                             "
            + "    layout: 'vertical',                               "
            + "    backgroundColor: '#FFFFFF',                       "
            + "    floating: true,                                   "
            + "    align: 'left',                                    "
            + "    x: 100,                                           "
            + "    verticalAlign: 'top',                             "
            + "    y: 70                                             "
            + "},                                                    "
            + "tooltip: {                                            "
            + "    formatter: function() {                           "
            + "        return '<b>'+ this.series.name +'</b><br/>'+  "
            + "            this.x +': '+ Highcharts.numberFormat(this.y);"
            + "    }                                                 " //
            + "}}";

    @Override
    public String getDescription() {
        return "Chart configured with both JSON notation and Java API. ";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();
        chart.setJsonConfig(JSON_CONF);
        Configuration conf = chart.getConfiguration();
        conf.setSeries(new ListSeries(getRawData()));
        return chart;
    }

    private Number[] getRawData() {
        return new Number[] { 29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6,
                148.5, 216.4, 194.1, 95.6, 54.4 };
    }
}
