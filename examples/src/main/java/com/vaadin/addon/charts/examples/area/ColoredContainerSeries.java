package com.vaadin.addon.charts.examples.area;

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.ChartDataSeries;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.PlotOptionsArea;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.server.data.DataSource;
import com.vaadin.server.data.ListDataSource;
import com.vaadin.ui.Component;

@SkipFromDemo
public class ColoredContainerSeries extends AbstractVaadinChartExample {

    protected class Test {
        private int number;
        private String name;

        public Test(final int number, final String name) {
            this.number = number;
            this.name = name;
        }

        public Integer getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.AREA);

        Configuration conf = chart.getConfiguration();

        conf.setTitle(new Title("Colored ContainerDataSeries"));

        conf.addxAxis(new XAxis());
        YAxis yAxis = new YAxis();
        yAxis.setTitle("Numbers");
        conf.addyAxis(yAxis);

        Collection<Test> col =new ArrayList<>();
        col.add(new Test(10, "TEN"));
        col.add(new Test(11, "ELEVEN"));
        col.add(new Test(12, "TWELVE"));
        DataSource<Test> ds = new ListDataSource<>(col);
        ChartDataSeries<Test> chartDS= new ChartDataSeries(ds);

        chartDS.setName("Test Series");
        chartDS.setYValueProvider(Test::getNumber);
        chartDS.setNameProvider(Test::getName);

        PlotOptionsArea plotOptions = new PlotOptionsArea();
        plotOptions.setFillColor(SolidColor.CORNFLOWERBLUE);
        plotOptions.setColor(SolidColor.GOLDENROD);
        chartDS.setPlotOptions(plotOptions);

        // conf.setPlotOptions(plotOptions);

        conf.setSeries(chartDS);

        chart.drawChart(conf);

        return chart;
    }


}
