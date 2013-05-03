package com.vaadin.addon.charts.demoandtestapp;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsBar;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.ui.Component;

public class JavaApiBasicBarTest extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Replicates Basic Bar example from HightCharts demo gallery.";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.BAR);
        Configuration configuration = chart.getConfiguration();
        chart.setHeight("450px");
        chart.setWidth("100%");

        // title: {
        // text: 'Historic World Population by Region'
        // },
        // subtitle: {
        // text: 'Source: Wikipedia.org'
        // },

        configuration.getTitle().setText("Historic World Population by Region");

        configuration.getSubTitle().setText("Source: Wikipedia.org");

        // xAxis: {
        // categories: ['Africa', 'America', 'Asia', 'Europe', 'Oceania'],
        // title: {
        // text: null
        // }
        // },
        configuration.getxAxis().setCategories("Africa", "America", "Asia",
                "Europe", "Oceania");

        // yAxis: {
        // min: 0,
        // title: {
        // text: 'Population (millions)',
        // align: 'high'
        // }
        // },

        Axis yAxis = configuration.getyAxis();
        yAxis.setMin(0d);
        yAxis.setTitle(new Title("Population (millions)"));
        yAxis.getTitle().setVerticalAlign(VerticalAlign.HIGH);

        // tooltip: {
        // formatter: function() {
        // return ''+
        // this.series.name +': '+ this.y +' millions';
        // }
        // },

        // TODO we should always just suggest using non JS api (pointFormat,
        // valueDecimals, valuePrefix etc).
        configuration.getTooltip().setFormatter(
                "this.series.name +': '+ this.y +' millions'");

        // plotOptions: {
        // bar: {
        // dataLabels: {
        // enabled: true
        // }
        // }
        // },
        PlotOptionsBar plotOptions = new PlotOptionsBar();
        plotOptions.setDataLabels(new Labels(true));
        configuration.setPlotOptions(plotOptions);

        // legend: {
        // layout: 'vertical',
        // align: 'right',
        // verticalAlign: 'top',
        // x: -100,
        // y: 100,
        // floating: true,
        // borderWidth: 1,
        // backgroundColor: '#FFFFFF',
        // shadow: true
        // },

        Legend legend = configuration.getLegend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setHorizontalAlign(HorizontalAlign.RIGHT);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setX(-100d);
        legend.setY(100d);
        legend.setFloating(true);
        legend.setBorderWidth(1d);
        legend.setBackgroundColor("#FFFFFF");
        legend.setShadow(true);

        // credits: {
        // enabled: false
        // },

        configuration.disableCredits();

        // series: [{\n"
        // + "                name: 'Year 1800',\n"
        // + "                data: [107, 31, 635, 203, 2]\n"
        // + "        }, {\n" + "                name: 'Year 1900',\n"
        // + "                data: [133, 156, 947, 408, 6]\n"
        // + "        }, {\n" + "                name: 'Year 2008',\n"
        // + "                data: [973, 914, 4054, 732, 34]\n"
        // + "        }]\n" + "}

        ListSeries ls = new ListSeries();
        ls.setName("Year 1800");
        ls.setData(107, 31, 635, 203, 2);
        configuration.addSeries(ls);
        ls = new ListSeries();
        ls.setName("Year 1900");
        ls.setData(133, 156, 947, 408, 6);
        configuration.addSeries(ls);
        ls = new ListSeries();
        ls.setName("Year 1900");
        ls.setData(973, 914, 4054, 732, 34);
        configuration.addSeries(ls);

        chart.drawChart(configuration);
        return chart;
    }

}
