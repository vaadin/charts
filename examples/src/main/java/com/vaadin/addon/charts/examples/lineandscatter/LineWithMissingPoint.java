package com.vaadin.addon.charts.examples.lineandscatter;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.YAxis;
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
        // FIXME remove toString() once enums are used in model (CHARTS-159)
        configuration.getChart().setType(ChartType.LINE.toString());
        configuration.getChart().setMarginRight(130);
        configuration.getChart().setMarginBottom(25);

        configuration.getTitle().setText("Monthly Average Temperature");
        configuration.getSubTitle().setText("Source: WorldClimate.com");

        configuration.getxAxis().setCategories(
                new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
                        "Aug", "Sep", "Oct", "Nov", "Dec" });

        YAxis yAxis = configuration.getyAxis();
        yAxis.setMin(-5d);
        yAxis.setTitle(new Title("Temperature (°C)"));
        yAxis.getTitle().setAlign(VerticalAlign.HIGH.toString());

        configuration
                .getTooltip()
                .setFormatter(
                        "'<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C'");

        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setDataLabels(new DataLabels(true));
        configuration.setPlotOptions(plotOptions);

        Legend legend = configuration.getLegend();
        legend.setLayout(LayoutDirection.VERTICAL.toString());
        legend.setAlign(HorizontalAlign.RIGHT.toString());
        legend.setVerticalAlign(VerticalAlign.TOP.toString());
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
