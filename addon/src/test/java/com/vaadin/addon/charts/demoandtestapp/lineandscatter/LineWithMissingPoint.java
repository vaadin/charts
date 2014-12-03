package com.vaadin.addon.charts.demoandtestapp.lineandscatter;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.SkipFromDemo;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.ui.Component;

@SkipFromDemo
public class LineWithMissingPoint extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Basic Line with missing point";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();
        chart.setHeight("450px");
        chart.setWidth("100%");

        Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.LINE);
        configuration.getChart().setMarginRight(130);
        configuration.getChart().setMarginBottom(25);

        configuration.getTitle().setText("Monthly Average Temperature");
        configuration.getSubTitle().setText("Source: WorldClimate.com");

        configuration.getxAxis().setCategories("Jan", "Feb", "Mar", "Apr",
                "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

        Axis yAxis = configuration.getyAxis();
        yAxis.setMin(-5d);
        yAxis.setTitle(new Title("Temperature (°C)"));
        yAxis.getTitle().setVerticalAlign(VerticalAlign.HIGH);

        configuration
                .getTooltip()
                .setFormatter(
                        "'<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C'");

        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setDataLabels(new Labels(true));
        configuration.setPlotOptions(plotOptions);

        Legend legend = configuration.getLegend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setHorizontalAlign(HorizontalAlign.RIGHT);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setX(-10d);
        legend.setY(100d);
        legend.setBorderWidth(0);

        DataSeries ds = new DataSeries();
        DataSeriesItem item = new DataSeriesItem(1, 2);
        item.setName("a");
        ds.add(item);
        item = new DataSeriesItem(2, 2);
        item.setName("b");
        ds.add(item);
        item = new DataSeriesItem(3, 2);
        item.setName("c");
        ds.add(item);
        item = new DataSeriesItem(4, null);
        item.setName("d");
        ds.add(item);
        item = new DataSeriesItem(5, 2);
        item.setName("e");
        ds.add(item);
        item = new DataSeriesItem(6, 2);
        item.setName("f");
        ds.add(item);

        configuration.addSeries(ds);

        chart.drawChart(configuration);
        System.out.println(configuration);
        return chart;
    }

}
