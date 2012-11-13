package com.vaadin.addon.charts.demoandtestapp;

import com.vaadin.addon.charts.Chart;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class BasicTest extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "T";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();
        String str = "{\n"
                + "        chart: {\n"
                + "                type: 'bar'\n"
                + "        },\n"
                + "        title: {\n"
                + "                text: 'Historic World Population by Region'\n"
                + "        },\n"
                + "        subtitle: {\n"
                + "                text: 'Source: Wikipedia.org'\n"
                + "        },\n"
                + "        xAxis: {\n"
                + "                categories: ['Africa', 'America', 'Asia', 'Europe', 'Oceania'],\n"
                + "                title: {\n"
                + "                        text: null\n"
                + "                }\n"
                + "        },\n"
                + "        yAxis: {\n"
                + "                min: 0,\n"
                + "                title: {\n"
                + "                        text: 'Population (millions)',\n"
                + "                        align: 'high'\n"
                + "                }\n"
                + "        },\n"
                + "        tooltip: {\n"
                + "                formatter: function() {\n"
                + "                        return ''+\n"
                + "                        this.series.name +': '+ this.y +' millions';\n"
                + "                }\n" + "        },\n"
                + "        plotOptions: {\n" + "                bar: {\n"
                + "                        dataLabels: {\n"
                + "                                enabled: true\n"
                + "                        }\n" + "                }\n"
                + "        },\n" + "        legend: {\n"
                + "                layout: 'vertical',\n"
                + "                align: 'right',\n"
                + "                verticalAlign: 'top',\n"
                + "                x: -100,\n" + "                y: 100,\n"
                + "                floating: true,\n"
                + "                borderWidth: 1,\n"
                + "                backgroundColor: '#FFFFFF',\n"
                + "                shadow: true\n" + "        },\n"
                + "        credits: {\n" + "                enabled: false\n"
                + "        },\n" + "        series: [{\n"
                + "                name: 'Year 1800',\n"
                + "                data: [107, 31, 635, 203, 2]\n"
                + "        }, {\n" + "                name: 'Year 1900',\n"
                + "                data: [133, 156, 947, 408, 6]\n"
                + "        }, {\n" + "                name: 'Year 2008',\n"
                + "                data: [973, 914, 4054, 732, 34]\n"
                + "        }]\n" + "}";

        chart.drawChart(str);
        chart.setHeight("450px");
        chart.setWidth("100%");
        return chart;
    }

}
