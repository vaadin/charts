package com.vaadin.addon.charts.demoandtestapp.lineandscatter;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.SkipFromDemo;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DashStyle;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

@SkipFromDemo
public class BasicLineWithRedGridLines extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Basic Line With Data Labels";
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
        yAxis.setGridLineColor(new SolidColor("red"));
        yAxis.setGridLineDashStyle(DashStyle.DASHDOT);
        yAxis.setGridLineWidth(3);

        XAxis xAxis = configuration.getxAxis();
        xAxis.setGridLineColor(new SolidColor(0, 255, 0, 0.5));
        xAxis.setGridLineWidth(4);

        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setDataLabels(new Labels(true));
        configuration.setPlotOptions(plotOptions);

        ListSeries ls = new ListSeries();
        ls.setName("Tokyo");
        ls.setData(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3,
                13.9, 9.6);
        configuration.addSeries(ls);
        ls = new ListSeries();
        ls.setName("New York");
        ls.setData(-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1,
                8.6, 2.5);
        configuration.addSeries(ls);
        ls = new ListSeries();
        ls.setName("Berlin");
        ls.setData(-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9,
                1.0);
        configuration.addSeries(ls);
        ls = new ListSeries();
        ls.setName("London");
        ls.setData(3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6,
                4.8);
        configuration.addSeries(ls);

        chart.drawChart(configuration);
        return chart;
    }

}
